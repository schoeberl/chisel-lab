# Lab 5: Seven Segment Decoder and Test

This lab is your first step towards your final project, the vending
machine.


## Seven Segment Decoder

You have to provide the table for the seven segment decoder
in ```SevenSegDecoder.scala```.
Read the [Basys 3](https://reference.digilentinc.com/reference/programmable-logic/basys-3/start?redirect=1)
documentation and the .xdc file on which segment is connected to which pin.

The project contains a nice tester that prints out your encoding.
Therefore, you can work on your table until getting it right without
needing to resort to the slow synthesis for the FPGA. Run the test with:


```
sbt "testOnly SevenSegDecoderSpec"
```

When you are happy with the test results, generate Verilog with:

```
sbt "runMain SevenSegDecoder"
```

Then create a Vivado project for the 7-segment decoder, synthesize
your design, and configure the FPGA. Test the 7-segment decoder with
the switches.

An .xdc file (```sevenseg.xdc```) with the pin definitions is available.

## Test Drive the Seven-Segment Display

The second part of the lab is your next step towards your vending machine.
It is described in  more detail in the **Vending Machine Specification**
in GitHub.

The circuit consist of the seven-segment decoder and the test circuit.
The test circuit shall drive the seven-segment display with a counter
to show all possible values. The counter shall count at a frequency
of around 2 Hz for easy following by a human.

For a start you can connect the clock input to a button for manual
clocking. However, the final solution shall use the 100 MHz clock and additional
hardware is needed to produce the 2 Hz timing.

Reuse your table from first part of the lab.

The test circuit shall be implemented in ```CountSevenSeg.scala```. You need
to instantiate the ```SevenSegDecoder``` module, add your test circuit,
and connect the module.

A simple test is provided, which you can run with

```
sbt "testOnly SevenSeqCountSpec"
```

and explore the waveform with GTKWave.

When you are happy with the test results, generate Verilog with:

```
sbt "runMain CountSevenSeg"
```

Then create a Vivado project for the 7-segment display test, synthesize
your design, and configure the FPGA.

An .xdc file (```sevenseg-cnt.xdc```) with the pin definitions is available.
For more information look into the
[Basys 3](https://reference.digilentinc.com/reference/programmable-logic/basys-3/start?redirect=1)
documentation. You can also find the pin definitions in the
[Schematic of the IO](https://reference.digilentinc.com/basys3/refmanual#basic_io).

When your design is working in the FPGA, show it to a TA for an OK
tick in the list. **This is part of your grade.**





