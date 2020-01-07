package exercises

import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class Count42 extends Module {
    val io = IO(new Bundle {
      val out = Output(UInt(8.W))
    })

    // implement the counter here
    // Remeber to connect the output of the register to the output of the module

}
