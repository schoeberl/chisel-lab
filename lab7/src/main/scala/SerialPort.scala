import chisel3._
import chisel3.util._

/**
  * This is the top level to develop the display multiplexing circuit.
  * The multiplexing circuit is in the DisplayMultiplexer.
  */
class SerialPort(frequ: Int) extends Module {
  val io = IO(new Bundle {
    val tx = Output(Bool())
  })

}

// generate Verilog
object SerialPort extends App {
  emitVerilog(new SerialPort(100000000))
}