# Lab 6: Test Drive the Seven-Segment Display

This lab is your next step towards your vending machine.
It is described in  more detail in the **Vending Machine Specification**
(available in DTU Inside), Exercise 1b.

The circuit consist of the seven-segment decoder and the test circuit

Reuse your table from last week (lab5) and add it to ```SevenSegDec.scala```.

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

When your design is working in the FPGA, show it to a TA for an ok
tick in the list.


