sudo apt install authbind sqlite3 raspberrypi-kernel-headers openjdk-11-jdk-headless zip iftop atop iotop nmap netcat vim screen libncurses5-dev build-essential fakeroot rsync git flex bison libssl-dev
sudo apt update
sudo apt upgrade
curl -LJO -H 'Accept: application/octet-stream' https://github.com/WiringPi/WiringPi/releases/download/2.61-1/wiringpi-2.61-1-arm64.deb
sudo dpkg -i wiringpi-2.61-1-arm64.deb
sudo reboot