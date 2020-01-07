package exercises

import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class Count42PeekPoke(c: Count42) extends PeekPokeTester(c) {
    for (i <- 0 until 100) {
        expect(c.io.out, i%43)
        step(1)
    }
}

object Count42Tester extends App{
    assert(chisel3.iotesters.Driver(() => new Count42) { c => new Count42PeekPoke(c) })
    println("SUCCESS!!")        // if all expect statement are not succesful
}