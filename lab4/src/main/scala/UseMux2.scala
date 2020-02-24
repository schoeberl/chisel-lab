import chisel3._

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