# The Vending Machine

This folder contains the setup of the full Vending Machine project.
The detailed specification is available at:
[Vending Machine Specification](https://cn.inside.dtu.dk/cnnet/filesharing/download/e712d72e-278e-4fef-a6ed-03eb20134acc)


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
sbt "test:runMain VendingSimulation"
```

However, this simulation is not yet complete. It will be extended in the next
days. You can get an updated version with ```git pull``` (when you have
retrived the repo with git clone) or by downloading a new .zip file and copying
the new ```VendingSimulation.scala``` to your working folder.