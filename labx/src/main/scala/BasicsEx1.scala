package exercises

import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class MACorACM extends Module {
    val io = IO(new Bundle {
        val a = Input(UInt(8.W))
        val b = Input(UInt(8.W))
        val c = Input(UInt(8.W))
        val sel = Input(Bool())
      
        val z  = Output(UInt(16.W))
    })

    // describe circuit here
}


