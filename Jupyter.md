

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
