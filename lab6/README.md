# Lab 6: Multiplexed Seven-Segment Display

In this lab you will extend your seven-segment decoder to display
four digits in a time-multiplexed manner.
This lab is your next step towards your vending machine.

The Basys 3 FPGA board has four so-called seven-segment displays
consisting of individual light emitting diodes (LED).
The display uses time-multiplexing where the 4 digits share
the 8 signals controlling the individual LED segments.
An additional 4 signals are used to enable the individual digits.
The idea is to turn on the digits one at a time and repeat
this a rate of roughly 1 KHz. A human observer will not see this
alternating activation. This solution requires only 12 pins on the FPGA

All LEDs in a given digit have the anode terminal connected to a transistor,
which itself is connected to the corresponding AN signal. 
All LEDs with the same label (A, B, C, ... , G, DP), one in each digit,
have their cathode terminals connected to the same signal. The actual design use bipolar transistors and is explained in more detail in the
[Basys 3 Reference Manual](https://digilent.com/reference/programmable-logic/basys-3/reference-manual)

Build a synchronous circuit in Chisel for a multiplexed seven-segment display
to display four hexadecimal digits  on the LED display.
The 16 switches are used as input for the four digits.

A simple solution is to display the numbers in hexadecimal.
However, humans usually
expect numbers in decimal notation. Therefore, you might (optional) extend this circuit
to perform binary to binary-coded-decimal (BCD) conversion. The Chisel book
contains the Scala code to generate such a table.

Sketch the circuit on a piece of paper! Do not turn on any PC before
you have done your design. Remember: When you write Chisel you are
describing the circuit you designed, you are not *programming* Chisel.

However, there is one issue that needs to be solved:
the 100 MHz from the clock input will not work as refresh frequency for
the multiplexing of the LED display.
We need to use a logical tick generation to multiplex the display at about 1 kHz.
All registers are clocked by the main clock of 100 MHz. However, the component
that does the multiplexing (state machine, or counter, or ...) will be enabled
only for one clock cycle every 1 ms. Is this similar to the tick generation from
last week?


Reuse ```SevenSegDec.scala``` from your former lab exercise.
To run the simulation (test) execute:

```
sbt test
```

and explore the waveform with GTKWave. The test runs just for
200 clock cycles. To see any useful results you need to switch
your multiplexer with a high frequency (your counter counts only
up to a few clock cycles). The waveform can be found in folder
```test_run_dir```

When you are happy with the test result, change your maximum count
value to generate a tick at around 1 kHz, and generate Verilog with:

```
sbt run
```

Then create a Vivado project for the display multiplexer, synthesize
your design, and configure the FPGA.

An .xdc file (```display.xdc```) with the pin definitions is available.
For more information look into the
[Basys 3](https://reference.digilentinc.com/reference/programmable-logic/basys-3/start?redirect=1)
documentation. You can also find the pin definitions in the
[Schematic of the IO](https://reference.digilentinc.com/basys3/refmanual#basic_io).

**When your design is working in the FPGA, show it to a TA for an OK
tick in the list! This is part of your grade**

For those not having an FPGA available at the moment there is a simulation
of the 7-segment display available. It is not perfect, but you can test
if you get your multiplexing correct. As with the waveform tester,
switch the display at a higher frequency (e.g., every 20 clock cycles), to
see results in the simulation.

You run the simulation of the display with:

```
sbt test:run
```

