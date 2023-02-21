
import Swapper.State
import chisel3._
import chisel3.util._
import heap.Indexed

object Swapper {

  class Request(params: Heap.Parameters) extends Bundle {
    import params._
    val values = Input(Vec(2, Indexed(log2Ceil(n+1).W, UInt(w.W))))
    val valid = Input(Bool())
    val ready = Output(Bool())
  }
  object State extends ChiselEnum {
    val Idle, WriteFirst, WriteSecond = Value
  }

}

class Swapper(params: Heap.Parameters) extends Module {
  import params._

  val io = IO(new Bundle {
    val req = new Swapper.Request(params)
    val mem = new HeapMemory.WriteAccess(params)
  })

  val stateReg = RegInit(State.Idle)
  val valuesReg = Reg(Vec(2, Indexed(log2Ceil(n+1).W, UInt(w.W))))

  io.req.ready := 0.B
  io.mem := DontCare
  io.mem.valid := 0.B

  switch(stateReg) {
    is(State.Idle) {
      stateReg := Mux(io.req.valid, State.WriteFirst, State.Idle)
      valuesReg := io.req.values
      io.req.ready := 1.B
    }
    is(State.WriteFirst) {
      stateReg := State.WriteSecond

      io.mem.index := valuesReg(0).index
      io.mem.value := valuesReg(1).item
      io.mem.valid := 1.B
    }
    is(State.WriteSecond) {
      stateReg := State.Idle

      io.mem.index := valuesReg(1).index
      io.mem.value := valuesReg(0).item
      io.mem.valid := 1.B
    }
  }

}
