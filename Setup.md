
# Tool Setup

This documents describes how to setup all tools on Linux/Ubuntu
and on Windows.
Here is a summary of the tools we need:

 * [Java OpenJDK 8 or later](https://adoptopenjdk.net/)
 * [sbt](https://www.scala-sbt.org/)
 * [IntelliJ](https://www.jetbrains.com/idea/download/) (the free Community version)
 * [Vivado WebPACK](https://www.xilinx.com/products/design-tools/vivado/vivado-webpack.html)
 * [GTKWave](http://gtkwave.sourceforge.net/)
 * make, git (for command line usage) (alternatively, a GUI Git client, see the section further down)

## Ubuntu Virtual Machine

The easiest way to get all tools installed is to use the Ubuntu virtual machine
provided at:

 * [Ubuntu VM](https://patmos-download.compute.dtu.dk/de2lab.zip)

The user is ```de2lab``` and the password is ```de2lab```. You need the free
[VMWare Player](https://my.vmware.com/en/web/vmware/free#desktop_end_user_computing/vmware_workstation_player/15_0)
for this virtual machine. Note, that this VM is BIG. The .zip file is 44 GB.


## Chisel

Chisel is *just* a library for Scala. And Scala is just a language that executes
on the Java virtual machine (JVM) and uses the Java library. Therefore, you need to have
[Java OpenJDK 8 or later](https://adoptopenjdk.net/) installed on your laptop.

For working on the command line you should also install
[sbt](https://www.scala-sbt.org/), the Scala build tool.
Please note that installing sbt will make the IntelliJ-build process a lot easier as well.

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
 * Install sbt according to the instructions from [sbt download](https://www.scala-sbt.org/download.html)
 * Install IntelliJ and the Scala plugin with a launch shortcut in favorites
 * Shortcut to GtkWave on desktop

Instead of running the apt commands manually you can also run the provided ```setup.sh```.

## Windows 10

 * Install Vivado (see above) and the Digilent board files
 * Install OpenJDK 8 or later from [AdoptOpenJDK](https://adoptopenjdk.net/)
 * Install [sbt](https://www.scala-sbt.org/)
 * Install [IntelliJ](https://www.jetbrains.com/idea/download/)
   * The community edition
   * Include Create Desktop Shortcut
 * Start IntelliJ to finish the setup
   * Select the light UI theme (if you prefer)
   * On the featured plugins select Install for Scala
   * When importing a project, select the JDK you installed before
     * On Project JDK select *New*
     * Select *JDK*
     * Select the path to your OpenJDK 8 installation, usually something like ```C:\Program Files\AdoptOpenJDK\jdk-8.0.232.09-hotspot\```
 * Download the [GTKWave binaries](https://sourceforge.net/projects/gtkwave/files/)
   * Select the latest release that matches the pattern ```gtkwave-{release number}-bin-win32```
   * Extract the downloaded ```.zip``` into a directory of choice
   * Run the executable ```gtkwave.exe``` in the folder ```gtkwave\bin\```
   * Put a link to the executable on the desktop
 * Copy the PDF of the [Chisel Book](http://www.imm.dtu.dk/~masca/chisel-book.html) on the desktop
 * Install a [git client](https://git-scm.com/download/win)
	* If you have no prior experience using git, check the followings links for an introduction to the git workflow and the advantages of version-control systems. [1](https://www.youtube.com/watch?v=SWYqp7iY_Tc), [2](https://www.freecodecamp.org/news/what-is-git-and-how-to-use-it-c341b049ae61/). 
	Do note that most git tutorials emphasize command line usage, but this is by no means a must. There exist several great GUI git clients, examples of which are [Github Desktop](https://desktop.github.com/) and [Fork](https://fork.dev/).
 

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

 * Install OpenJDK 8 or later from [AdoptOpenJDK](https://adoptopenjdk.net/)
 * Install sbt with ```brew install sbt```
 * Install [GTKWave](http://gtkwave.sourceforge.net/)
 * Install [IntelliJ](https://www.jetbrains.com/idea/download/)
   * The community edition
   * Include Create Desktop Shortcut
 * Start IntelliJ to finish the setup
   * Select the light UI theme (if you prefer)
   * On the featured plugins select Install for Scala
   * When importing a project, select the JDK you installed before
     * On Project JDK select *New*
     * Select *JDK*
     * Select the path to your OpenJDK installation

## Common Error

Here we collect common issues when installing the tools. Also refer to the [FAQ](FAQ.md)

