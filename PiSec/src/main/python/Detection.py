import datetime

import RPi.GPIO as GPIO
import os
import time

import sqlite3
from sqlite3 import Error

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

    sql = "INSERT INTO alert(date, time, recording) VALUES (?, ?, ?)"

    cur = conn.cursor()
    cur.execute(sql, alert)
    conn.commit()
    return cur.lastrowid

def alert(date, time , path):
    database = r"PiSec.db"

    # create a database connection
    conn = create_connection(database)
    with conn:
        # create a new record in motion
        alert = (date, time, path)
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
    time.sleep(30)
    print('Set up')
    value = GPIO.input(sensor)
    while True:
        if value!=0:
            GPIO.output(led, GPIO.HIGH)
            dateTime = str(datetime.datetime.now())
            dateSplit = dateTime.split(" ")
            date = dateSplit[0]
            timeAccurate = dateSplit[1]
            timeSplit = timeAccurate.split(".")
            time = timeSplit[0]
            path = '~/Pictures/' + dateTime.replace(" ", "_") + '.jpg'
            os.system('libcamera-jpeg -o '+ path)
            print('Motion')
            alert(date, time, path)
            time.sleep(10) #change to 30s when implement recording
        else:
            GPIO.output(led, GPIO.LOW)
            time.sleep(2)
            print('No motion')
        time.sleep(3)
        value = GPIO.input(sensor)
except KeyboardInterrupt:
    print('Quit')
    GPIO.cleanup()



