## This file is a general .xdc for the Nexys A7-100T
## Cut down to the Minimum for the Hello World example

set_property IOSTANDARD LVCMOS33 [get_ports *]

## Clock signal
set_property -dict { PACKAGE_PIN E3    IOSTANDARD LVCMOS33 } [get_ports { clock }]; # IO_L12P_T1_MRCC_35 Sch=clk100mhz
create_clock -add -name sys_clk_pin -period 10.00 -waveform {0 5} [get_ports {clock}];

## LEDs
set_property -dict { PACKAGE_PIN H17   IOSTANDARD LVCMOS33 } [get_ports { io_led }];  # IO_L18P_T2_A24_15 Sch=io_led

##Buttons
set_property -dict { PACKAGE_PIN M18   IOSTANDARD LVCMOS33 } [get_ports { reset }]; #IO_L4N_T0_D05_14 Sch=btnu
