# Lab Session 2

## Combinational Circuits in Chisel

The lab session will show you...

After the lab you will have a good overview of the tools used to
edit and compile a hardware design coded in Chisel.
You will be able to synthesize this design and configure the FPGA board.

We assume that you have downloaded the complete lab material from GitHub
and it is placed in folder ```chisel-lab```.

### Background Reading

 * This lab is loosely based on Chapter 2 of
*[Digital Design with Chisel](http://www.imm.dtu.dk/~masca/chisel-book.html)*

### Compiling and Testing of Combinational Circuits

Today's lab topic is to describe selected combinational building blocks in Chisel.
We provide the testing code for your circuits. You have completed all exercises
when all tests (run with ```sbt test```) complete without an error.

With IntelliJ import the lab1 project as follows:

 * Start IntelliJ
 * Click *Import Project*, or on a running IntelliJ: *File - New -
Project from Existing Source...*
 * Navigate to ```.../chisel-lab/lab2``` and select the file ```build.sbt```, press *Open*
 * Make sure to select JDK 1.8 (not Java 11!)
 * Press OK on the next dialog box



Then navigate to the Chisel component ```Majority``` by following in the Project navigator along: *lab2 - src - main - scala - Majority. Open ```Majority``` with a double click.

This is a Chisel component that shall implement the majority voting of three
signals (```a```, ```b```, and ```c```). Majority voting means that the output of
the circuit is the majority of the inputs, e.g., if ```a==1```, ```b==0```, and
```c==1``` the result shall be ```1```. See Dally 3.6 for a solution in VHDL.
Your task is to implement that circuit in Chisel.


Open the terminal at the bottom of the IntelliJ window start the build process with:
```
sbt test
```
to compile and test your project.

In the *Run* window you should see something like:
```
TBD
```

### Optional: Generating Hardware

In lab1 you have learned how to generate hardware to run in an FPGA.
In this lab exercise you used testing to run your combinational circuites.
However, we can also run those circuits on the FPGA boards and use switches
and LEDs to test the circuits.

TODO: add a description and .xdc file for exploring the majority voting.
