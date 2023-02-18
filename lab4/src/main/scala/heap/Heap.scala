
import chisel3._
import chisel3.util._

object Heap {
  case class Parameters(
                             n: Int, // Maximum number of elements
                             k: Int, // Order of the heap
                             w: Int, // item width
                           ) {
    require(isPow2(k), "The order of the heap needs to be a power of 2")
  }
  object Operation extends ChiselEnum {
    val Insert, RemoveRoot = Value
  }
  class Request(params: Heap.Parameters) extends Bundle {
    import params._
    val op = Input(Heap.Operation())
    val newValue = Input(UInt(w.W))
    val root = Output(UInt(w.W))
    val empty = Output(Bool())
    val full = Output(Bool())
    val valid = Input(Bool())
    val ready = Output(Bool())
  }
}

class Heap(params: Heap.Parameters) extends Module {
  import params._

  val io = IO(new Heap.Request(params))

  object Components {
    val fetcher = Module(new Fetcher(params))
    val swapper = Module(new Swapper(params))
    val memory = Module(new HeapMemory(params))
    val control = Module(new HeapControl(params))
    val heapifier = Module(new Heapifier(params))
    val maxFinder = Module(new MaxFinder(params))
  }

  when(Components.control.io.mem.requestRead) {
    Components.memory.io.read.index := Components.control.io.mem.read.index
    Components.memory.io.read.withSiblings := 0.B
  } otherwise {
    Components.memory.io.read <> Components.fetcher.io.mem
  }

  Components.control.io.mem.read.values := Components.memory.io.read.values
  Components.fetcher.io.mem.values := Components.memory.io.read.values

  when(Components.control.io.mem.write.valid) {
    Components.memory.io.write <> Components.control.io.mem.write
  } otherwise {
    Components.memory.io.write <> Components.swapper.io.mem
  }

  Components.fetcher.io.req <> Components.control.io.fetcher
  Components.fetcher.io.res <> Components.maxFinder.io.fetcher
  Components.heapifier.io.maxFinder <> Components.maxFinder.io.res
  Components.heapifier.io.res <> Components.control.io.heapifier
  Components.heapifier.io.swapper <> Components.swapper.io.req

  Components.control.io.req.valid := io.valid
  Components.control.io.req.op := io.op
  Components.control.io.req.newValue := io.newValue

  io.ready := Components.control.io.req.ready
  io.empty := Components.control.io.req.empty
  io.full := Components.control.io.req.full
  io.root := Components.memory.io.root

}
