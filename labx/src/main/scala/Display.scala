import chisel3._
import chisel3.util._

class Display extends Module {
  val io = IO(new Bundle {
    val sw = Input(UInt(16.W))
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })

  val sevSeg = WireInit(0.U)

  // ***** your code starts here *****
  sevSeg := "b1111111".U
  // ***** your code ends here *****

  io.seg := ~sevSeg
  io.an := "b1110".U
}

// generate Verilog
object Display extends App {
  chisel3.Driver.execute(args, () => new Display())
}


