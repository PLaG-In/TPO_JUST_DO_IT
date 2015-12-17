import urllib, socket, smtplib
 
site_pages = [
'http://google.com',
'http://domain.com/page2.html',
'http://domain.com/page3.html'
]
 
failed_pages = [];
smtp_server = 'smtp.domain.com'
from_mail = 'from@domain.com'
to_emails = ['tester@domain.com', 'developer@domain.com']
 
def check_pages (pages):
	try:
		for page_url in pages:
			code = urllib.urlopen(page_url).getcode()
			print "{0} - {1}".format(page_url, code)
			if (code not in [200, 301]):
				failed_pages.append(page_url)
	except socket.error, e:
		print "Ping Error: ", e
 
def generate_message ():
	n = len(failed_pages)
	list = ""
	if (n > 0):
		list = "404 errors: \r\n"
		for failed_link in failed_pages:
			list = "\r\n".join((list, failed_link))
	else:
		list = "All links are correct"
	return list
 
def send_mail (text):
	body = "\r\n".join((
        "From: " + from_mail,
        "To: team",
        "Subject: Links checking finished",
        "",
        text
        ))
	server = smtplib.SMTP(smtp_server)
	server.sendmail(from_mail, to_emails, body)
	server.quit()
 
check_pages(site_pages)
 
send_mail(generate_message())
