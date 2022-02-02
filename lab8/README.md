# Lab 8: Test Drive the Seven-Segment Display

This lab is your next step towards your vending machine.
It is described in  more detail in the **Vending Machine Specification**
(available in DTU Learn), Exercise 1b.

The circuit consist of the seven-segment decoder and the test circuit.
The test circuit shall drive the seven-segment display with a counter
to show all possible values. The counter shall count at a frequency
of around 2 Hz for easy following by a human.

For a start you can connect the clock input to a button for manual
clocking. However, the final solution shall use the 100 MHz clock and additional
hardware is needed to produce the 2 Hz timing.

Reuse your table from last lab and add it to ```SevenSegDec.scala```.

The test circuit shall be implemented in ```CountSevenSeg.scala```. You need
to instantiate the ```SevenSegDec``` module, add your test circuit,
and connect the module.

A simple test is provided, which you can run with

```
sbt test
```

and explore the waveform with GTKWave.

When you are happy with the test results, generate Verilog with:

```
sbt run
```

Then create a Vivado project for the 7-segment display test, synthesize
your design, and configure the FPGA.

An .xdc file (```sevenseg-cnt.xdc```) with the pin definitions is available.
For more information look into the
[Basys 3](https://reference.digilentinc.com/reference/programmable-logic/basys-3/start?redirect=1)
documentation. You can also find the pin definitions in the
[Schematic of the IO](https://reference.digilentinc.com/basys3/refmanual#basic_io).

When your design is working in the FPGA, show it to a TA for an ok
tick in the list.


