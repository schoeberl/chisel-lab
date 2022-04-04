import chisel3._
import chisel3.util._

class BlackVendingA() extends HasBlackBoxPath {
  val io = IO(new Bundle {
    val price = Input(UInt(5.W))
    val coin2 = Input(Bool())
    val coin5 = Input(Bool())
    val buy = Input(Bool())
    val releaseCan = Output(Bool())
    val alarm = Output(Bool())
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })
  addPath("./src/main/verilog/VendingA.v")
}

class BlackVendingB() extends HasBlackBoxPath {
  val io = IO(new Bundle {
    val price = Input(UInt(5.W))
    val coin2 = Input(Bool())
    val coin5 = Input(Bool())
    val buy = Input(Bool())
    val releaseCan = Output(Bool())
    val alarm = Output(Bool())
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })
  addPath("./src/main/verilog/VendingB.v")
}


class VendingMachine(maxCount: Int) extends Module {
  val io = IO(new Bundle {
    val price = Input(UInt(5.W))
    val coin2 = Input(Bool())
    val coin5 = Input(Bool())
    val buy = Input(Bool())
    val releaseCan = Output(Bool())
    val alarm = Output(Bool())
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })

  // Switch between the two vending machines here.
  val vending = Module(new BlackVendingA)
  // val vending = Module(new BlackVendingB)
  vending.io <> io
}

// generate Verilog
object VendingMachine extends App {
  emitVerilog(new VendingMachine(100000))
}


