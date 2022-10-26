#Based on https://stackoverflow.com/questions/64505/sending-mail-from-python-using-smtp
import smtplib #smtp library import
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

def send_email(email):
    msg = MIMEMultipart()
    msg['Subject'] = "PiSec Alert"                                  #input("Subject: ")
    message = "Dear User, your PiSec device has detected suspicious activity in Room 0"                                              #input("Message: ")
    msg.attach(MIMEText(message))

    mailserver = smtplib.SMTP('smtp.gmail.com',587)
    # identify ourselves to smtp gmail client
    #mailserver.ehlo()
    # secure our email with tls encryption
    mailserver.starttls()
    # re-identify ourselves as an encrypted connection
    #mailserver.ehlo()
    mailserver.login('mikusvancans@gmail.com', 'bfzdsmtkagchtbhm')

    mailserver.sendmail('mikusvancans@gmail.com', email, msg.as_string())
    print("Email sent!")
    mailserver.quit()

