package exercises

import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}


class MACorACMPeekPoke(c: MACorACM) extends PeekPokeTester(c) {
  // The parameter c refers to the module we are testing. To access signals from MACorACM 
  // the prefix "c." is therefore needed.
  val tests = 50
  import scala.util.Random
  
    poke(c.io.sel, true)              // Set the selector signal to 1/true.B
  for (i <- 0 until tests) {          // For loop to make 50 tests
    val in_a = Random.nextInt(16)     // Sets the scala values in_a, in_b and in_c to a random integer value 
    val in_b = Random.nextInt(16)     // Between 0 and 16, 16 not included. This range is chosen to avoid overflow.
    val in_c = Random.nextInt(16)
    poke(c.io.a, in_a)                // Sets in MACorACM inputs a, b and c to the random integer value.
    poke(c.io.b, in_b)
    poke(c.io.c, in_c)
    expect(c.io.z, (in_a*in_b)+in_c)  // Tests if the module under test computes the output correctly. If not an 
                                      // an error is thrown
    step(1)                           // Advance the simlation by one clock cycle. Not needed for this test.
  }
    
  poke(c.io.sel, false)               // This loop test the other operation the MACorACM module should compute 
  for (i <- 0 until tests) {
    val in_a = Random.nextInt(16)
    val in_b = Random.nextInt(16)
    val in_c = Random.nextInt(16)
    poke(c.io.a, in_a)
    poke(c.io.b, in_b)
    poke(c.io.c, in_c)
    expect(c.io.z, ((in_a+in_b)*in_c))
    step(1)
  }
}

object MACorACMTester extends App{
    assert(Driver(() => new MACorACM) {c => new MACorACMPeekPoke(c)}) // by using assert the next line will not be run 
    println("SUCCESS!!")                                            // if all expect statement are not succesful
}