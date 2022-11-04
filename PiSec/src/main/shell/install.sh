sudo apt install authbind sqlite3 openjdk-11-jdk-headless
sudo apt update
sudo apt upgrade
curl -LJO -H 'Accept: application/octet-stream' https://github.com/WiringPi/WiringPi/releases/download/2.61-1/wiringpi-2.61-1-arm64.deb
sudo dpkg -i wiringpi-2.61-1-arm64.deb
sudo reboot