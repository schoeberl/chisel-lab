# Lab 9: Multiplexed Seven-Segment Display

This lab is your next step towards your vending machine.
It is described in more detail in the [Vending Machine Specification](https://learn.inside.dtu.dk/d2l/common/viewFile.d2lfile/Content/L2NvbnRlbnQvZW5mb3JjZWQvNjAyMzgtT0ZGRVJJTkctNjMzODE1L1ZlbmRpbmdNYWNoaW5lLnBkZg/VendingMachine.pdf?ou=60238),
Exercise 2.

The circuit shall be a multiplexed seven-segment display.
The switches are used as input for the four digits.
Similar to last lab you need to generate timing with counter
to switch the display and drive the multiplexer.

Reuse ```SevenSegDec.scala``` from your former lab exercise. Run:

```
sbt "testOnly DisplaySpec"
```

and explore the waveform with GTKWave. The test runs just for
200 clock cycles. To see any useful results you need to switch
your multiplexer with a high frequency (your counter counts only
up to a few clock cycles). The waveform can be found in folder
```test_run_dir```

When you are happy with the test results, change your maximum count
value to generate a tick at around 1 kHz, and generate Verilog with:

```
sbt run
```

Then create a Vivado project for the display multplexer, synthesize
your design, and configure the FPGA.

An .xdc file (```display.xdc```) with the pin definitions is available.
For more information look into the
[Basys 3](https://reference.digilentinc.com/reference/programmable-logic/basys-3/start?redirect=1)
documentation. You can also find the pin definitions in the
[Schematic of the IO](https://reference.digilentinc.com/basys3/refmanual#basic_io).

When your design is working in the FPGA, show it to a TA for an OK
tick in the list.

For those not having an FPGA available at the moment there is a simulation
of the 7-segment display available. It is not perfect, but you can test
if you get your multiplexing correct. As with the waveform tester,
switch the display at a higher frequency (e.g., every 20 clock cycles), to
see results in the simulation.

You run the simulation of the display with:

```
sbt "testOnly DisplayRunner"
```

