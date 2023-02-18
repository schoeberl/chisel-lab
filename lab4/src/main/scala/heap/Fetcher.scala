
import Fetcher.State
import chisel3._
import chisel3.util._
import heap.{Indexed, ValidTagged}

object Fetcher {

  class Request(params: Heap.Parameters) extends Bundle {
    import params._
    val index = UInt(log2Ceil(n+1).W)
    val size = UInt(log2Ceil(n+2).W)
    val valid = Bool()
  }
  class Response(params: Heap.Parameters) extends Bundle {
    import params._
    val parent = Indexed(log2Ceil(n+1).W, UInt(w.W))
    val children = Vec(k, ValidTagged(Indexed(log2Ceil(n+1).W, UInt(w.W))))
    val valid = Bool()
  }
  object State extends ChiselEnum {
    val Idle, RequestParent, RequestChildren, ReceiveChildren = Value
  }
}

class Fetcher(params: Heap.Parameters) extends Module {
  import params._

  val io = IO(new Bundle {
    val req = Input(new Fetcher.Request(params))
    val res = Output(new Fetcher.Response(params))
    val mem = new HeapMemory.ReadAccess(params)
  })

  val stateReg = RegInit(State.Idle)
  val validReg = RegInit(0.B)

  val sizeReg = RegInit(0.U(log2Ceil(n+2).W))

  val parentIndexReg = RegInit(0.U(log2Ceil(n+1).W))
  val childIndexReg = RegInit(VecInit(Seq.fill(k)(0.U(log2Ceil(n+1).W))))
  val parentReg = RegInit(0.U(w.W))
  val childrenReg = RegInit(VecInit(Seq.fill(k)(0.U(w.W))))
  val maskReg = RegInit(VecInit(Seq.fill(k)(0.B)))
  io.res.parent := Indexed.fromTuple(parentReg -> parentIndexReg)

  io.res.children := (childrenReg, childIndexReg, maskReg).zipped.map { case (item, index, valid) =>
    ValidTagged(valid, Indexed.fromTuple(item -> index))
  }

  io.res.valid := 0.B
  io.mem.withSiblings := 0.B
  io.mem.index := parentIndexReg

  switch(stateReg) {
    is(State.Idle) {
      stateReg := Mux(io.req.valid, State.RequestParent, State.Idle)

      parentIndexReg := io.req.index
      sizeReg := io.req.size

      io.res.valid := validReg
    }
    is(State.RequestParent) {
      stateReg := State.RequestChildren

      childIndexReg := VecInit(Seq.tabulate(k)( i => (parentIndexReg << log2Ceil(k)).asUInt + (i+1).U))

      io.mem.index := parentIndexReg
    }
    is(State.RequestChildren) {
      stateReg := State.ReceiveChildren

      parentReg := io.mem.values(0)

      io.mem.index := childIndexReg(0)
      io.mem.withSiblings := 1.B
    }
    is(State.ReceiveChildren) {
      stateReg := State.Idle

      childrenReg := io.mem.values
      maskReg := VecInit(childIndexReg.map(_ < sizeReg))

      validReg := 1.B
    }
  }

}
