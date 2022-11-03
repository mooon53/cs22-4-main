sudo touch /etc/authbind/byport/80
sudo touch /etc/authbind/byport/443
sudo chmod 500 /etc/authbind/byport/80
sudo chmod 500 /etc/authbind/byport/443
sudo chown $USER /etc/authbind/byport/80
sudo chown $USER /etc/authbind/byport/443
sudo reboot