
# Tool Setup

This documents describes how to setup all tools on Linux/Ubuntu
and on Windows.
Here is a summary of the tools we need:

 * [Java OpenJDK 8](https://adoptopenjdk.net/)
 * [sbt](https://www.scala-sbt.org/)
 * [IntelliJ](https://www.jetbrains.com/idea/download/) (the free Community version)
 * [Vivado WebPACK](https://www.xilinx.com/products/design-tools/vivado/vivado-webpack.html)
 * [GTKWave](http://gtkwave.sourceforge.net/)
 * make, git (for command line usage)

## Chisel

Chisel is *just* a library for Scala. And Scala is just a language that executes
on the Java virtual machine (JVM) and uses the Java library. Therefore, you need to have
[Java OpenJDK 8](https://adoptopenjdk.net/) installed on your laptop.
*It is important to use version 8 (also called 1.8) of Java, not a newer one.*

For working on the command line you should also install
[sbt](https://www.scala-sbt.org/), the Scala build tool.

A nice editor for Chisel/Scala is
[IntelliJ](https://www.jetbrains.com/idea/download/). At the first start
of IntelliJ download the Scala plugin (at Download featured plugins).

## Vivado

Vivado is the synthesize tool from Xilinx for the Basys3 FPGA board.
The WebPACK edition is freely available at:
https://www.xilinx.com/products/design-tools/vivado/vivado-webpack.html

 * Download [Vivado WebPACK](https://www.xilinx.com/products/design-tools/vivado/vivado-webpack.html)
   * You need to register with Xilinx
   * To save some space you can deselect all devices except Artix-7
 * For Linux the installer executable can be run with ```bash Xilinx...```
   * see
     [Digilent Installation](https://reference.digilentinc.com/vivado/installing-vivado/start)
     for instructions
 * Install cable drivers and Digilent board files (according to the above instructions)

## Ubuntu/Linux

This is the log when I prepared the Ubuntu virtual machine for the DE 2 lab. It may be helpful to setup
your Linux system for the DE 2 lab.

 * Install Ubuntu 18.04 LTS, max disc set to 80 GB and 4 GB for memory
 * uid: de2lab, pwd: de2lab
 * Set time and time zone (important for further installation!)
 * Settings - Power - Blank screen: never, Privacy - Aut. Screen Lock - OFF
 * Copy the chisel book onto the desktop
   * That is my current snapshot
 * Install Vivado (in home folder)
   * Install cable drivers
   * Get digilent board definitions
 * Install Java JDK and other tools with:
 * ```sudo apt install openjdk-8-jdk git make gtkwave```
 * Install sbt with:
```
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
sudo apt-get update
sudo apt-get install sbt
```
 * Install IntelliJ and the Scala plugin with a launch shortcut in favorites

Instead of running the apt commands manually you can also run the provided ```setup.sh```.

## Windows 10

 * Install Vivado (see above) and the Digilent board files
 * Install OpenJDK 8 from [AdoptOpenJDK](https://adoptopenjdk.net/)
 * Install [sbt](https://www.scala-sbt.org/)
 * Install [IntelliJ](https://www.jetbrains.com/idea/download/)
   * The community edition
   * Include Create Desktop Shortcut
 * Start IntelliJ to finish the setup
   * Select the light UI theme (if you prefer)
   * On the featured plugins select Install for Scala
   * When importing a project, *select the JDK 1.8* you installed before (not Java 11!)
     * On Project JDK select *New*
     * Select *JDK*
     * Select the path to your OpenJDK 8 installation, usually ```C:\Program Files\AdoptOpenJDK\jdk-8.0.232.09-hotspot\'''
 * Install [GTKWave](http://gtkwave.sourceforge.net/)
   * Put a link to the executable on the desktop
 * Copy the PDF of the [Chisel Book](http://www.imm.dtu.dk/~masca/chisel-book.html) on the desktop
 * Install a [git client](https://git-scm.com/download/win)
 

The installation can be checked with the first exercise in the lab. Or a quick, partial
check with following commands in the Windows Powershell:

```
javac
sbt
```

## macOS

Vivado is not supported under macOS, however, the Chisel tool flow runs fine
on the Mac. You can simulate your design on the Mac and use a virtual machine
(e.g., with Ubuntu) to synthesize your design with Vivado.

 * Install OpenJDK 8 from [AdoptOpenJDK](https://adoptopenjdk.net/)
 * Install sbt with ```brew install sbt'''
 * Install [GTKWave](http://gtkwave.sourceforge.net/)
 * Install [IntelliJ](https://www.jetbrains.com/idea/download/)
   * The community edition
   * Include Create Desktop Shortcut
 * Start IntelliJ to finish the setup
   * Select the light UI theme (if you prefer)
   * On the featured plugins select Install for Scala
   * When importing a project, *select the JDK 1.8* you installed before (not Java 11!)
     * On Project JDK select *New*
     * Select *JDK*
     * Select the path to your OpenJDK 8 installation

## Common Error

Her we collect common issues when installing the tools.

 * Wrong Java version. Version later then JDK 8 do not work with Chisel/Scala

