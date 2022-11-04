# from laptop: scp -r <tomcat home folder> <user>@<pi host name>:tomcat/
# from laptop: scp -r <Project folder>/src/main/python <user>@<pi host name>:
cd ~/tomcat/apache-tomcat-10.1.1
authbind --deep ./bin/startup.sh
cd ~/python
python Detection.py