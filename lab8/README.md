# Lab 8 and 9: Multiplexed Seven-Segment Display

This lab is your next step towards your vending machine.
It is described in more detail in the [Vending Machine Specification](https://cn.inside.dtu.dk/cnnet/filesharing/download/e712d72e-278e-4fef-a6ed-03eb20134acc),
Exercise 2.

The circuit consist of the multiplexed seven-segment display.
The switches are used as input for the four digits.
Similar to last lab you need to generate timing with counter
to switch the display and drive the multiplexer.

Reuse ```SevenSegDec.scala``` from Lab 6.

```
sbt test
```

and explore the waveform with GTKWave. The test runs just for
100 clock cycles. To see any useful results you need to switch
your multiplexer with a high frequency (your counter counts only
up to maybe a single digit number).

When you are happy with the test results, change your maximum count
value for about 1 kHz, and generate Verilog with:

```
sbt run
```

Then create a Vivado project for the display test, synthesize
your design, and configure the FPGA.

An .xdc file (```display.xdc```) with the pin definitions is available.
For more information look into the
[Basys 3](https://reference.digilentinc.com/reference/programmable-logic/basys-3/start?redirect=1)
documentation. You can also find the pin definitions in the
[Schematic of the IO](https://reference.digilentinc.com/basys3/refmanual#basic_io).

When your design is working in the FPGA, show it to a TA for an OK
tick in the list.


