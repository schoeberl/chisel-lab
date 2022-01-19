import chisel3._
import chisel3.util._

/**
  * This is the top level to develop the display multiplexing circuit.
  * The multiplexing circuit is in the DisplayMultiplexer.
  */
class Display(maxCount: Int) extends Module {
  val io = IO(new Bundle {
    val sw = Input(UInt(16.W))
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })

  val dispMux = Module(new DisplayMultiplexer(maxCount))

  // Simulate the price and sum input with the switches
  dispMux.io.price := io.sw(7, 0)
  dispMux.io.sum := io.sw(15, 8)

  // Connect the display
  io.seg := dispMux.io.seg
  io.an := dispMux.io.an
}

// generate Verilog
object Display extends App {
  emitVerilog(new Display(100000))
}