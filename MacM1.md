
# Mac M1 (Apple Silicon)

Using a Mac with Apple Silicon

 * Using Parallel to create a Windows 11 for ARM VM
 * Installed Vivado
 * Windows is missing the FTDI drivers - install Adapt from Digilent will add the missing drivers
 * Use Windows 11 Arm version to generate bitstream
   * With the drivers Vivado can configure and Putty can listen to the serial port
 * Configure from Mac with open OCD
   * see https://github.com/byu-cpe/BYU-Computing-Tutorials/wiki/Program-7-Series-FPGA-from-a-Mac-or-Linux-Without-Xilinx
     * Run it as sudo
   * Configuration (several A7 boards, e.g., Basys3, NexysA7) is in `7series.txt`:
```
# File: 7series.txt
interface ftdi
ftdi_device_desc "Digilent USB Device"
ftdi_vid_pid 0x0403 0x6010
# channel 1 does not have any functionality
ftdi_channel 0
# just TCK TDI TDO TMS, no reset
ftdi_layout_init 0x0088 0x008b
reset_config none
adapter_khz 10000

source [find cpld/xilinx-xc7.cfg]
source [find cpld/jtagspi.cfg]
init

puts [irscan xc7.tap 0x09]
puts [drscan xc7.tap 32 0]  

puts "Programming FPGA..."
pld load 0 Hello.bit
exit
```
   * Get the .bit file (from the Windows machine) and set the name in the `7series.txt`
   * Configure with: `openocd -f 7series.txt`


