import chisel3._
import chisel3.util._

/**
  * A broken 5:1 multiplexer
  */

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

  io.y := 1.U
  switch(io.sel) {
    is("b000".U) { io.y := io.a }
    is("b001".U) { io.y := 1.U }
    is("b010".U) { io.y := io.c }
    is("b011".U) { io.y := io.d }
    is("b101".U) { io.y := io.e }
  }
}

object MuxHW extends App {
  emitVerilog(new Mux5(), Array("--target-dir", "generated"))
}