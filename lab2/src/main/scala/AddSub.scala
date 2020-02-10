import chisel3._

class AddSub extends Module {
  val io = IO(new Bundle {
    val a = Input(Bool())
    val b = Input(Bool())
    val sel = Input(Bool())
    val y = Output(Bool())
  })

  val a = io.a
  val b = io.b
  val sel = io.sel
  val res = Wire(Bool())

  // ***** your code starts here *****

  res := b

  // ***** your code ends here *****

  io.y := res
}


