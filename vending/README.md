# The Vending Machine

This folder contains the setup of the full Vending Machine project.
The detailed specification is available at:
[Vending Machine Specification](https://learn.inside.dtu.dk/d2l/common/viewFile.d2lfile/Content/L2NvbnRlbnQvZW5mb3JjZWQvNjAyMzgtT0ZGRVJJTkctNjMzODE1L1ZlbmRpbmdNYWNoaW5lLnBkZg/VendingMachine.pdf?ou=60238)


## Testing the Design

When writing your code, always run tests. Best approach is a test-driven development
where you write tests together with the design. This repository contains a simple
test to get you started. It presses some buttons and generates a waveform file that
you can view with GTKWave (remember to open a ```.vcd``` file with ```File - Open New Tab```).
You can run the test with:

```
sbt test
```

Extend the test with your own tests.



## Generating the Verilog Files for the FPGA

Finally, the real test is in the FPGA baord. Generate the Verilog file with:

```
sbt run
```

Create a Xilinx Vivado project with the generatd Verilog file
```VendingMachine.v``` and the constraint file ```vending.xdc```.



## Simulating the Basys3 Board

Furthermore, we can also run the vending machine hardware with a simulation
of the Basys3 board, as follows:

```
sbt test:run
```

The simulation contains the 7-segment  display simulation that is included
in lab8. As with the waveform tester, switch the display at a higher
frequency (e.g., every 20 clock cycles) to avoid simulating forever.

The simulation contains three buttons for the VM that are connected to
the inputs ```con2```, ```con5```, and ```buy```. With one click you can
toggle to pressed, with another clock you can toggle back to release.
The status is also written out to the command line.

The ```Exit``` button stops the simulation.

The next row simulates the 16 green LEDs. Two LEDs are connected to
```releaseCan``` and ```alarm```.

The bottom row contains 16 check boxes to simulate the 16 switches.
The right 5 check boxes are connected to the ```price``` input.