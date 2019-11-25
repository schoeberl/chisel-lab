#!/bin/sh
sudo apt update
sudo apt -y install curl

echo install openjdk8
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get -y install openjdk-8-jdk

echo Install sbt and thereby scala
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add
sudo apt-get update
sudo apt-get -y install sbt

sudo apt -y install git make gtkwave

echo "Install python for ubuntu"
sudo apt-get -y install python3.7

echo Install pip3
sudo apt install -y python3-pip

echo Install Jupyter
pip3 install jupyter

echo Install Almond Scala - kernel for Jupyter
SCALA_VERSION=2.12.8
ALMOND_VERSION=0.2.1
curl -Lo coursier https://git.io/coursier
chmod +x coursier
./coursier bootstrap -r jitpack -i user -I user:sh.almond:scala-kernel-api_$SCALA_VERSION:$ALMOND_VERSION sh.almond:scala-kernel_$SCALA_VERSION:$ALMOND_VERSION -o almond
./almond --install

echo Please reboot to complete installation
