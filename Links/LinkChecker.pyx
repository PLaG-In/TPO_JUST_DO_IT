# -*- coding: UTF-8 -*-

import socket
import sys
import urllib
from HTMLParser import HTMLParser
from urlparse import urlparse
from urlparse import urljoin


failed_links = {}
links = {}
stack = []
main_url = ''


class url_finder(HTMLParser):

    def __init__(self):
        HTMLParser.__init__(self)
        self.links = []

    def handle_starttag(self, tag, attrs):
        attrs = dict(attrs)
        if "a" == tag:
            try:
                self.links.append(attrs["href"])
            except:
                pass


def handle_url():
    while len(stack) > 0:
        url = stack.pop()
        parser = url_finder()
        print url
        try:
            if url in links.keys() or get_domain_name(urlparse(url).netloc) != main_url:
                break
            try:
                response = urllib.urlopen(url)
                parser.feed(response.read())
            except:
                failed_links[url] = "401"
                continue
            code = response.getcode()
            links[url] = code
            if code in [200, 301]:
                for found_links in parser.links:
                    if found_links == "/" or found_links[0] == '"' or found_links[0] == "{":
                        continue
                    if urlparse(found_links).scheme not in ["https", "http", "ftp"]:
                        found_links = urljoin(url, found_links)
                    if get_domain_name(urlparse(found_links).netloc) == main_url:
                        if found_links not in links.keys():
                            stack.append(found_links)
            else:
                failed_links[url] = code
        except socket.error, e:
            failed_links[url] = "Ping Error: ", e
    return


def get_domain_name(netloc_):
    domain = netloc_[netloc_.find(".") + 1: len(netloc_)]
    if domain.find(".") == -1:
        return netloc_
    else:
        return domain


def generate_output_file():
    f = open("report.txt", "w")
    f.write("All links:\n")
    for key in links.keys():
        f.write("{0} - {1}\n".format(key, links[key]))
    f.write("\nBroken links:\n")
    for key in failed_links.keys():
        f.write("{0} - {1}\n".format(key, failed_links[key]))

    
if __name__ == "__main__":
    if len(sys.argv) < 2:
        exit()
    if urlparse(sys.argv[1]).scheme not in ["https", "http", "ftp"]:
        print ("Error: Expected absolute link")
        exit()
    main_url = get_domain_name(urlparse(sys.argv[1]).netloc)
    stack.append(sys.argv[1])
    handle_url()
    generate_output_file()
