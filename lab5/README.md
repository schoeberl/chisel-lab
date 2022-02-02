# Lab 5: A Simple Tester

In this lab you shall write test code for a 5:1 multiplexer. The interface
of the multiplexer is:

```scala
class Mux5 extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(8.W))
    val b = Input(UInt(8.W))
    val c = Input(UInt(8.W))
    val d = Input(UInt(8.W))
    val e = Input(UInt(8.W))
    val sel = Input(UInt(3.W))
    val y = Output(UInt(8.W))
  })
  .....
}
```

Write the test code for the ```Mux5``` implementation. Try to **not**
look into the implementation. The implementation has errors.
Your job is to show those errors with your test code. You do not
need to correct the errors in ```Mux5```.

There is no *starting* code given. You need to start from scratch
(look into an older lab for test examples). You have two options:

 * Call the tester from an ```App``` (= main), place your code
in ```src/main/scala```, and run your test with ```sbt run```, or
 * Call the tester from a ScalaTest, place your code
   in ```src/test/scala```, and run your test with ```sbt test```.

## Background Reading

 * Chapter 3 of
*[Digital Design with Chisel](http://www.imm.dtu.dk/~masca/chisel-book.html)*

## Use IntelliJ

With IntelliJ import the lab project as follows:

 * Start IntelliJ
 * Click *Import Project*, or on a running IntelliJ: *File - New -
Project from Existing Source...*
 * Navigate to ```.../lab5``` and select the file ```build.sbt```, press *Open*
 * Press OK on the next dialog box

