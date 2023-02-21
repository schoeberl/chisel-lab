
import MaxFinder.ParentWrapper
import chisel3._
import chisel3.util._
import heap.{Delay, Indexed}

object MaxFinder {
  class Result(params: Heap.Parameters) extends Bundle {
    import params._
    val largest = Indexed(log2Ceil(n+1).W, UInt(w.W))
    val parent = Indexed(log2Ceil(n+1).W, UInt(w.W))
    val isParent = Bool()
    val valid = Bool()
  }
  object ParentWrapper {
    def apply[T <: Data](isParent: Bool, item: T): ParentWrapper[T] = {
      val w = Wire(new ParentWrapper(chiselTypeOf(item)))
      w.item := item
      w.isParent := isParent
      w
    }
  }
  class ParentWrapper[T <: Data](gen: T) extends Bundle {
    val isParent = Bool()
    val item = gen
  }
}

class MaxFinder(params: Heap.Parameters) extends Module {
  import params._

  val io = IO(new Bundle {
    val fetcher = Input(new Fetcher.Response(params))
    val res = Output(new MaxFinder.Result(params))
  })

  val pipelineDepth = log2Ceil(k) + 1

  val maxChild = io.fetcher.children.reduceTree { (l, r) =>
    RegNext(Mux(r.valid && r.data.item > l.data.item, r, l))
  }
  val maxItem = RegNext(Mux(maxChild.valid && maxChild.data.item > io.fetcher.parent.item, ParentWrapper(0.B, maxChild.data), ParentWrapper(1.B, io.fetcher.parent)))
  io.res.largest := maxItem.item
  io.res.parent := io.fetcher.parent
  io.res.isParent := maxItem.isParent
  io.res.valid := Delay(io.fetcher.valid, pipelineDepth)

}
