
# Tool Setup

This documents describes how to setup all tools on an Linux/Ubuntu
laptop. We also include pointers for the Windows setup.

## Chisel

Chisel is *just* a library for Scala. And Scala is just a language that executes
on the JVM and uses the Java library. Therefore, you need to have Java JDK (1.8)
installed on your laptop, which you probably already have.

For working on the command line you should also install
[sbt](https://www.scala-sbt.org/), the Scala build tool.

## Vivado

Vivado is the synthesize tool from Xilinx for the Basys3 FPGA board.
The WebPACK edition is freely available at:
https://www.xilinx.com/products/design-tools/vivado/vivado-webpack.html

 * Download [Vivado WebPACK](https://www.xilinx.com/products/design-tools/vivado/vivado-webpack.html)
   * You need to register with Xilinx

 * For Linux the installer executable can be run with ```bash Xilinx...```
   * see
     [Digilent Installation](https://reference.digilentinc.com/vivado/installing-vivado/start)
     for instructions
 * Install cable drivers and Digilent board files (according to the above instructions)

## Ubuntu/Linux

This is the log when I prepared the VM for the DE 2 lab. It may be helpful to setup
your Linux system for the DE 2 lab.

 * Install Ubuntu 18.04 LTS, max disc set to 80 GB and 4 GB for memory
 * uid: de2lab, pwd: de2lab
 * Set time and time zone (important for further installation!)
 * Settings - Power - Blank screen: never, Privacy - Aut. Screen Lock - OFF
 * Install Vivado (in home folder)
   * Install cable drivers
   * Get digilent board definitions
 * ```sudo apt install openjdk-8-jdk-headless```
 * Install InelliJ and the Scala plugin with a launch shortcut in favorites
 * ```sudo apt install git make gtkwave```
 * Install sbt with:
```
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
sudo apt-get update
sudo apt-get install sbt
```
