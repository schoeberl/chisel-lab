import chisel3._

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


class UseMux extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(1.W))
    val dout = Output(UInt(1.W))
  })

  val a = 0.U
  val b = 1.U
  val sel = io.sel
  val res = Wire(UInt())

  // ***** your code starts here *****

  // create a Mux2 component and connect it to a, b, sel, and res
  res := b

  // ***** your code ends here *****

  io.dout := res
}

