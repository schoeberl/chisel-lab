
import Heapifier.State
import chisel3._
import chisel3.util._

object Heapifier {

  class Response(params: Heap.Parameters) extends Bundle {
    import params._
    val largest = UInt(log2Ceil(n+1).W)
    val swapped = Bool()
    val valid = Bool()
  }
  object State extends ChiselEnum {
    val Idle, IssueSwap, WaitForSwap = Value
  }

}

class Heapifier(params: Heap.Parameters) extends Module {
  import params._

  val io = IO(new Bundle {
    val res = Output(new Heapifier.Response(params))
    val maxFinder = Input(new MaxFinder.Result(params))
    val swapper = Flipped(new Swapper.Request(params))
  })

  val stateReg = RegInit(State.Idle)
  val swapRequired = !io.maxFinder.isParent
  val swapRequiredReg = RegInit(0.B)

  io.res.largest := io.maxFinder.largest.index
  io.res.swapped := swapRequiredReg
  io.res.valid := 0.B

  io.swapper.values(0) := io.maxFinder.parent
  io.swapper.values(1) := io.maxFinder.largest
  io.swapper.valid := 0.B

  switch(stateReg) {
    is(State.Idle) {
      stateReg := Mux(RegNext(!io.maxFinder.valid) && io.maxFinder.valid, State.IssueSwap, State.Idle)
    }
    is(State.IssueSwap) {
      swapRequiredReg := swapRequired
      when(swapRequired) {
        io.swapper.valid := 1.B
        stateReg := State.WaitForSwap
      } otherwise {
        stateReg := State.Idle
        io.res.valid := 1.B
        io.res.swapped := 0.B
      }
    }
    is(State.WaitForSwap) {
      stateReg := Mux(io.swapper.ready, State.Idle, State.WaitForSwap)
      when(io.swapper.ready) {
        io.res.valid := 1.B
      }
    }
  }

}