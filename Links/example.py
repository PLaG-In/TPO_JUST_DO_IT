# -*- coding: utf8 -*-

from urllib2 import Request, urlopen, URLError, HTTPError
from urlparse import urljoin
import lxml.html


arrayFindLinks = list()
arrayLinks = list()
arrayDeadLinks = list()
dictFindLinks = {}
dictErrorLinks = {}
startUrl = ''

def ParsingPage(html, currentUrl):
    try:
        doc = lxml.html.document_fromstring(html)
    except BaseException, e:
        return
    
    for element, attribute, link, pos in doc.iterlinks():
        link = urljoin(currentUrl, link)
        if (not(link in arrayLinks) and not(link in arrayFindLinks) and ( -1 != link.find(startUrl, 0, len(link)))):
            arrayFindLinks.append(link)

def LoadPage(url):
    request = Request(url)
    try:
        response = urlopen(request)
        dictFindLinks[url] = response.getcode()
        print url, " - ", response.getcode()
    except BaseException, e:
        print url, " - ", e
        dictErrorLinks[url] = e
        dictFindLinks[url] = e
        arrayLinks.append(url)
        del arrayFindLinks[0]
        return 0

    else:
        page = response.read()
        ParsingPage(page, url)
        arrayLinks.append(url)
        del arrayFindLinks[0]


startUrl = "http://tpotest.aderkin.ru"
arrayFindLinks.append(startUrl)
print ("Start URL: ") , startUrl

while (len(arrayFindLinks)):
    if (not(arrayFindLinks[0] in arrayLinks)):
        LoadPage(arrayFindLinks[0])
    else:
        del arrayFindLinks[0]


fileLinks = open("report.txt", "w")
fileLinks.write("Все найденные ссылки: " + '\n')
for keys in dictFindLinks:
    line = str(keys) + " - " + str(dictFindLinks[keys])
    fileLinks.write(line + '\n')
    
fileLinks.write('\n' + "Битые ссылки: " + '\n')
for keys in dictErrorLinks:
    line = str(keys) + " - " + str(dictErrorLinks[keys])
    fileLinks.write(line + '\n')
    
fileLinks.close()
print ('end')
