
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
   * Select the light UI theme
   * On the featured plugins select Install for Scala
   * When importing a project, select the JDK 1.8 as installed before (not Java 11!)
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

## Jupyter notebook on Windows 10 (Optional)

To use Jupyter notebooks with Chisel following additional steps are needed.

To install Windows Subsystem for Linux [WSL](https://docs.microsoft.com/en-us/windows/wsl/install-win10), either use the following guide for manual install without Windows store or, if on your personal computer, as described in the second section:


### Non-Windows store installation

*This installation version is for shared Windows machines.*

Ubuntu shall be installed in a shared folder.

In Powershell as administrator:
```
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Windows-Subsystem-Linux

Invoke-WebRequest -Uri https://aka.ms/wsl-ubuntu-1804 -OutFile Ubuntu.appx -UseBasicParsing
Rename-Item ./Ubuntu.appx ./Ubuntu.zip
Expand-Archive ./Ubuntu.zip ./Ubuntu
cd .\Ubuntu\

#To open:
.\ubuntu1804.exe
```

After running those commands, set up a username and password.
When writing a password in Linux, it does not show anything. This is normal.

After this, run the install script as described under Ubuntu install script


### Automated using Windows store

In a Powershell with administrator (Search powershell from start menu and right click)

```
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Windows-Subsystem-Linux
```

Go to Windows store and search for Ubuntu. Click install.

After installation, search for Ubuntu in your start menu.

First time opening, create a new Unix user. Choose a username and password. **Notice that passwords in linux does not show when typing!** This is a security feature.

### Ubuntu install script

In the Ubuntu terminal write the following commands (To copy/paste in a terminal running under Windows, just right click. If something is marked, it will be copied. If nothing is marked, it will paste):
```
wget https://raw.githubusercontent.com/schoeberl/chisel-lab/master/setup.sh

chmod +x setup.sh
./setup.sh
```

After running setup.sh you should close the terminal and open it again. You should now have access to Jupyter Notebooks by writing ```jupyter notebook```

An url will be showed in the terminal. Copy this and paste into the browser.

## Common Error

Her we collect common issues when installing the tools.

 * Wrong Java version. Version later then JDK 8 do not work with Chisel/Scala

