# Lab 4: Components and Small Sequential Circuits

The lab session will show you how to use components, called modules in Chisel,
and how to describe small sequential circuits.

We assume that you have downloaded the complete lab material from GitHub
and it is placed in folder ```chisel-lab```.

Similar to the first two labs you import the projects in IntelliJ.
We will not repeat these instructions here. See the former labs for the
instruction.

We provide the testing code for your circuits. You have completed all
(Chisel code) exercises when all tests (run with ```sbt test```) complete
without an error.

## Background Reading

 * This lab is loosely based on Chapter 2, 4, and 6 of
*[Digital Design with Chisel](http://www.imm.dtu.dk/~masca/chisel-book.html)*

   
### Using Components

#### Use the Mux2

We provide a ```Mux2``` component, a 2:1 multiplexer with single-bit inputs.
For the first exercise edit ```UseMux2```. There you shall create an instance of
```Mux2``` and connect it to the signals: ```a```, ```b```, ```sel```, and ```res```.
Run the specific test for this exercise with:

```
 sbt "testOnly UseMux2Spec"
```
 
### Design a 4:1 Multiplexer

In digital design we often build larger, more complex designs by combining
smaller and simpler components. In this exercise you shall build a 4:1 multiplexer
out of 2:1 multiplexers (the ```Mux2``` component.) The ```Mux4``` component has
a 2-bit select input (```sel```), four inputs (```a```, ```b```, ```c```, and ```d```),
and one output (```y```). Select ```a``` when ```sel``` is ```"b00"```,
```b``` when ```sel``` is ```"b001"```, and so on.

Before writing Chisel code draw the solution on a sheet of paper and discuss the solution.
When you are confident in your design (done on paper), code it up in Chisel in ```Mux4```.
Run the test for the 4:1 multiplexer with

```
sbt "testOnly Mux4Spec"
```
 
### Build a Two Clock Cycles Delay

The next exercise is a simple circuit containing two registers that form a 2 clock
cycles delay. This circuit is also used as an input synchronizer for external asynchronous
signals. Below you find the schematics of this circuit.

![Synchronizer](../figures/synchronizer.svg)

Run the test for the two clock cycles delay with

```
sbt "testOnly DelaySpec"
```

### Schematics from Chisel Code

We have several different ways to specify digital circuits. We can draw block
diagrams as a visual representation or write Chisel code for simulation and
synthesis of the circuit. It is important to be able to translate between those
spcifications. In this part of the exercise you get Chisel code and shall draw
a schematic of te circuit. Discuss your schematic with a TA.

#### Code Example 1

```
when(ok) {
  ligth := GREEN
} .otherwise {
  light := RED
}
```

### An App to Generate Verilog (Optional)

 * Write an App to generate Verilog for the Mux4 circuit
 * Adapt the .xdc file for the circuit
 * Gerate a Vivdao project
 * Synthesize and test

## Not yet used stuff below

### A Multiplexer (Mux)

![Mux](../figures/mux.svg)

A multplexer selects between different input signals. In the above figure
it is a 2:1 multiplexer. With ```sel``` we route either input ```a``` or
input ```b``` to output ```y```. We assume in this example that ```a```
is slected when ```sel``` is ```0``` or ```false```, otherwise  ```b```.

Open the ```Mux2``` component to implement the multiplexer.
You can test your implementation with:
```
sbt "testOnly Mux2Spec"
```

