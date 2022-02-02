## This file is a general .xdc for the Basys3 rev B board
## To use it in a project:
## - uncomment the lines corresponding to used pins
## - rename the used ports (in each line, after get_ports) according to the top level signal names in the project

set_property IOSTANDARD LVCMOS33 [get_ports *]


## Clock signal
set_property PACKAGE_PIN W5 [get_ports clock]

# BTN3 -  can be used as manual clock instead
#set_property PACKAGE_PIN C16 [get_ports clock]

create_clock -add -name sys_clk_pin -period 10.00 -waveform {0 5} [get_ports clock]


##Buttons
# btnR proposed as reset
#set_property PACKAGE_PIN T17 [get_ports btnR]
set_property PACKAGE_PIN T17 [get_ports reset]

##7 segment display
set_property PACKAGE_PIN W7 [get_ports {io_seg[0]}]
set_property PACKAGE_PIN W6 [get_ports {io_seg[1]}]
set_property PACKAGE_PIN U8 [get_ports {io_seg[2]}]
set_property PACKAGE_PIN V8 [get_ports {io_seg[3]}]
set_property PACKAGE_PIN U5 [get_ports {io_seg[4]}]
set_property PACKAGE_PIN V5 [get_ports {io_seg[5]}]
set_property PACKAGE_PIN U7 [get_ports {io_seg[6]}]
#set_property PACKAGE_PIN V7 [get_ports io_dp]
set_property PACKAGE_PIN U2 [get_ports {io_an[0]}]
set_property PACKAGE_PIN U4 [get_ports {io_an[1]}]
set_property PACKAGE_PIN V4 [get_ports {io_an[2]}]
set_property PACKAGE_PIN W4 [get_ports {io_an[3]}]
