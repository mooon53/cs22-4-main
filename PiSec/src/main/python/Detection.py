import datetime

import RPi.GPIO as GPIO
import os
from time import sleep, time

import sqlite3
from sqlite3 import Error

from Email import send_email
from WhatsAppMessage import send_whatsapp


def create_connection(db_file):
    """"create database connection"""
    conn = None
    try:
        conn = sqlite3.connect(db_file)
    except Error as e:
        print(e)

    return conn


def create_alert_record(conn, alert):
    """create new record in alert table"""
    sql = "INSERT INTO alert(date_time, recording) VALUES (?, ?)"

    cur = conn.cursor()
    cur.execute(sql, alert)
    conn.commit()
    return cur.lastrowid

def alert(dateTime , path):
    database = r"PiSec.db"
    # create a database connection
    conn = create_connection(database)
    with conn:
        # create a new record in motion
        alert = (dateTime, path)
        alert_id = create_alert_record(conn, alert)


# sudo nano /boot/config.txt
# at the end change dtoverlay line to dtoverlay=imx219
GPIO.setmode(GPIO.BOARD)
sensor = 11
led = 37
GPIO.setup(sensor, GPIO.IN)
GPIO.setup(led, GPIO.OUT)


try:
    print('Ready')
    sleep(30)
    print('Set up')
    value = GPIO.input(sensor)
    while True:
        if value != 0:
            GPIO.output(led, GPIO.HIGH)
            dateTime = str(round(time() * 1000))
            path = '~/Pictures/' + dateTime
            os.system('libcamera-vid -t 30000 --width 1920 --height 1080 -o ' + path + '.h264')
            os.system('ffmpeg -i ' + path + '.h264 ' + path + '.mp4')
            print('Motion')
            # send_whatsapp(31637171525)
            send_email("t.frauenfelder@student.utwente.nl")
            alert(dateTime, dateTime + '.mp4')
            sleep(10) #change to 30s when implement recording
        else:
            GPIO.output(led, GPIO.LOW)
            sleep(2)
            print('No motion')
        sleep(3)
        value = GPIO.input(sensor)
except KeyboardInterrupt:
    print('Quit')
    GPIO.cleanup()