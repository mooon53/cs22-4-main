import datetime

import RPi.GPIO as GPIO
from gpiozero import MotionSensor
import os
import time
import signal

# install gpiozero
# install picamera ->doesn't work on Pi 64 OS ->use libcamera

# sensor = MotionSensor(17)
#
# imageCount = 0
#
# def photo():
#     global imageCount
#     imageCount=imageCount+1
#     print('motion')
#     sleep(5)
#
# try:
#     print('ready')
#     while True:
#         sensor.when_motion = photo
# except KeyboardInterrupt:
#     pass

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
            date = str(datetime.datetime.now()).replace(" ", "_") + '.jpg'
            print(date)
            os.system('libcamera-jpeg -o ~/Pictures/'+date)
            print('Motion')
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


