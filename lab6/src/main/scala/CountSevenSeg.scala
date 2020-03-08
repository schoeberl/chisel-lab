import chisel3._
import chisel3.util._

class CountSevenSeg extends Module {
  val io = IO(new Bundle {
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })

  val sevSeg = WireInit("b1111111".U(7.W))

  // *** your code starts here


  // *** your code ends here

  io.seg := ~sevSeg
  io.an := "b1110".U
}

// generate Verilog
object CountSevenSeg extends App {
  chisel3.Driver.execute(args, () => new CountSevenSeg())
}