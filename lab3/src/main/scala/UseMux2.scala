import chisel3._

class UseMux2 extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(1.W))
    val dout = Output(UInt(1.W))
  })

  val a = 1.U
  val b = 0.U
  val sel = io.sel
  val res = Wire(UInt())

  // ***** your code starts here *****

  // create a Mux2 component and connect it to a, b, sel, and res

  // below is dummy code to make this example compile
  res := b

  // ***** your code ends here *****

  io.dout := res
}