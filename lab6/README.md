# Lab 6: Seven Segment Decoder

This lab is your first step towards your final project, the vending
machine.


### Seven Segment Decoder

You have to provide the table for the seven segment decoder
in ```SevenSegDecoder.scala```.
Read the [Basys 3](https://reference.digilentinc.com/reference/programmable-logic/basys-3/start?redirect=1)
documentation and the .xdc file on which segment is connected to which pin.

The project contains a nice tester that prints out your encoding.
Therefore, you can work on your table until getting it right without
needing to resort to the slow synthesis for the FPGA. Run the test with:


```
sbt test
```

When you are happy with the test results, generate Verilog with:

```
sbt run
```

Then create a Vivado project for the 7-segment decoder, synthesize
your design, and configure the FPGA. Test the 7-segment decoder with
the switches.

An .xdc file (```sevenseg.xdc```) with the pin definitions is available.

When your design is working in the FPGA, show it to a TA for an ok
tick in the list.


