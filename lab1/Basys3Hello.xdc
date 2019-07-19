## This file is a .xdc for the Basys3 rev B board
## Cut down to the Minimum for the Hello World example

set_property IOSTANDARD LVCMOS33 [get_ports *]

## Clock signal
set_property PACKAGE_PIN W5 [get_ports clock]
create_clock -add -name sys_clk_pin -period 10.00 -waveform {0 5} [get_ports clock]
 
## LED and reset button
set_property PACKAGE_PIN U16 [get_ports {io_led}]
set_property PACKAGE_PIN T17 [get_ports reset]
