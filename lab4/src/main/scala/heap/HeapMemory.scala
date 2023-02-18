
import chisel3._
import chisel3.util.{log2Ceil, UIntToOH}
import HeapMemory.split

object HeapMemory {

  class ReadAccess(params: Heap.Parameters) extends Bundle {
    import params._
    val index = Output(UInt(log2Ceil(n+1).W))
    val withSiblings = Output(Bool())
    val values = Input(Vec(k, UInt(w.W)))
  }

  class WriteAccess(params: Heap.Parameters) extends Bundle {
    import params._
    val index = Output(UInt(log2Ceil(n+1).W))
    val value = Output(UInt(w.W))
    val valid = Output(Bool())
  }

  // split UInt at given index (index is included in upper chunk)
  def split(index: UInt)(at: Int): (UInt,UInt) = index(index.getWidth - 1, at) -> index(at - 1, 0)


}

class HeapMemory(params: Heap.Parameters) extends Module {
  import params._


  val io = IO(new Bundle {
    val read = Flipped(new HeapMemory.ReadAccess(params))
    val write = Flipped(new HeapMemory.WriteAccess(params))
    val root = Output(UInt(w.W))
  })

  val banks = Seq.fill(k)(SyncReadMem(n/k, UInt(w.W)))
  val rootReg = RegInit(0.U(w.W))
  io.root := rootReg

  val (readRow, readColumn) = split(io.read.index - 1.U)(log2Ceil(k))
  val readValues = VecInit(banks.map(_.read(readRow)))
  io.read.values := readValues
  when(RegNext(!io.read.withSiblings)) {
    io.read.values(0) := Mux(RegNext(io.read.index === 0.U), rootReg, readValues(RegNext(readColumn)))
  }

  val (writeRow, writeColumn) = split(io.write.index - 1.U)(log2Ceil(k))
  val writeBankMask = UIntToOH(writeColumn).asBools
  when(io.write.valid) {
    when(io.write.index === 0.U) {
      rootReg := io.write.value
    } otherwise {
      banks.zip(writeBankMask).foreach { case (b,e) =>
        when(e) { b.write(writeRow, io.write.value) }
      }
    }
  }

}