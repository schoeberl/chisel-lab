import chisel3._
import chisel3.util._

class Decoder extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(2.W))
    val out = Output(UInt(4.W))
  })

  val sel = io.sel
  val dec = WireDefault(0.U)

  // ***** your code starts here *****

  // ***** your code ends here *****

  io.out := dec
}


