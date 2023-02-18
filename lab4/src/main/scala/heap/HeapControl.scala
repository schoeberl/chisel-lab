
import HeapControl.State
import chisel3._
import chisel3.util._

object HeapControl {
  class Request(params: Heap.Parameters) extends Bundle {
    import params._
    val op = Input(Heap.Operation())
    val newValue = Input(UInt(w.W))
    val empty = Output(Bool())
    val full = Output(Bool())
    val valid = Input(Bool())
    val ready = Output(Bool())
  }
  object State extends ChiselEnum {
    val Idle, WriteNewTail, IssueFetchUp, WaitForResUp, FetchTail, WriteTailToRoot, IssueFetchDown, WaitForResDown = Value
  }
}

class HeapControl(params: Heap.Parameters) extends Module {
  import params._

  val io = IO(new Bundle {
    val req = new HeapControl.Request(params)
    val mem = new Bundle {
      val read = new HeapMemory.ReadAccess(params)
      val write = new HeapMemory.WriteAccess(params)
      val requestRead = Output(Bool())
    }
    val fetcher = Output(new Fetcher.Request(params))
    val heapifier = Input(new Heapifier.Response(params))
  })

  val stateReg = RegInit(State.Idle)

  val sizeReg = RegInit(0.U(log2Ceil(n+2).W))
  val subtreeIndexReg = RegInit(0.U(log2Ceil(n+1).W))
  val newValueReg = RegInit(0.U(w.W))
  val tailReg = RegInit(0.U(w.W))

  def parent(index: UInt): UInt = ((index - 1.U) >> log2Ceil(k)).asUInt
  def firstChild(index: UInt): UInt = (index << log2Ceil(k)).asUInt + 1.U


  val tailIndex = sizeReg - 1.U
  val full = RegEnable(1.B, 0.B, sizeReg === n.U)

  io.req.empty := sizeReg === 0.U
  io.req.full := full
  io.req.ready := 0.B

  io.fetcher.index := subtreeIndexReg
  io.fetcher.size := sizeReg
  io.fetcher.valid := 0.B

  io.mem.read := DontCare
  io.mem.requestRead := 0.B
  io.mem.write := DontCare
  io.mem.write.valid := 0.B

  switch(stateReg) {
    is(State.Idle) {
      newValueReg := io.req.newValue
      io.req.ready := 1.B

      io.mem.read.index := tailIndex
      io.mem.requestRead := 1.B

      when(io.req.valid) {
        when(io.req.op === Heap.Operation.Insert) {
          stateReg := Mux(full, State.Idle, State.WriteNewTail)
        } otherwise {
          when(sizeReg < 2.U) {
            sizeReg := 0.U
            stateReg := State.Idle
          } otherwise {
            stateReg := State.FetchTail
          }
        }
      } otherwise {
        stateReg := State.Idle
      }
    }
    is(State.WriteNewTail) {
      io.mem.write.value := newValueReg
      io.mem.write.index := sizeReg
      io.mem.write.valid := 1.B

      sizeReg := sizeReg + 1.U
      subtreeIndexReg := parent(sizeReg)

      stateReg := Mux(sizeReg === 0.U, State.Idle, State.IssueFetchUp)
    }
    is(State.IssueFetchUp) {
      io.fetcher.valid := 1.B

      stateReg := State.WaitForResUp
    }
    is(State.WaitForResUp) {
      when(io.heapifier.valid) {
        when(io.heapifier.swapped && subtreeIndexReg =/= 0.U) {
          subtreeIndexReg := parent(subtreeIndexReg)
          stateReg := State.IssueFetchUp
        } otherwise {
          stateReg := State.Idle
        }
      } otherwise {
        stateReg := State.WaitForResUp
      }
    }
    is(State.FetchTail) {
      tailReg := io.mem.read.values(0)

      stateReg := State.WriteTailToRoot
    }
    is(State.WriteTailToRoot) {
      io.mem.write.value := tailReg
      io.mem.write.index := 0.U
      io.mem.write.valid := 1.B

      sizeReg := sizeReg - 1.U
      subtreeIndexReg := 0.U

      stateReg := Mux(sizeReg === 2.U, State.Idle, State.IssueFetchDown)
    }
    is(State.IssueFetchDown) {
      io.fetcher.valid := 1.B

      stateReg := State.WaitForResDown
    }
    is(State.WaitForResDown) {
      when(io.heapifier.valid) {
        when(io.heapifier.swapped && firstChild(io.heapifier.largest) < sizeReg) {
          subtreeIndexReg := io.heapifier.largest
          stateReg := State.IssueFetchDown

        } otherwise {
          stateReg := State.Idle
        }
      } otherwise {
        stateReg := State.WaitForResDown
      }
    }

  }

}
