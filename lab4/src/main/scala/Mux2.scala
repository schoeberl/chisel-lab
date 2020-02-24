import chisel3._

/**
  * Mux2 is a simple one-bit multiplexer.
  * Use it in UseMux2 and to build the Mux4 circuit.
  */

class Mux2 extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(1.W))
    val b = Input(UInt(1.W))
    val sel = Input(UInt(1.W))
    val y = Output(UInt(1.W))
  })

  io.y := io.a
  when (io.sel === 1.U) {
    io.y := io.b
  }
}