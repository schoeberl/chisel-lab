import chisel3._
import chisel3.internal.firrtl.Width
import chisel3.util.log2Ceil

import java.io.{File, PrintWriter}
import scala.annotation.tailrec

package object heap {

  class TestHeap extends Heap(Heap.Parameters(8, 2, 8))

  object Delay {
    def apply[T <: Data](x: T, cycles: Int = 1): T = if (cycles == 0) x else RegNext(Delay(x, cycles - 1), init = 0.U.asTypeOf(x))
  }


  object Indexed {
    def fromTuple[T <: Data](init: (T, UInt)): Indexed[T] = {
      val w = Wire(new Indexed(init._2.getWidth.W, chiselTypeOf(init._1)))
      w.item := init._1
      w.index := init._2
      w
    }

    def apply[T <: Data](indexWidth: Width, typeGen: => T): Indexed[T] = new Indexed(indexWidth, typeGen)
  }

  class Indexed[T <: Data](indexWidth: Width, typeGen: => T) extends Bundle {
    val item = typeGen
    val index = UInt(indexWidth)
  }

  object ValidTagged {
    def apply[T <: Data](typeGen: => T): ValidTagged[T] = new ValidTagged(typeGen)

    def apply[T <: Data](valid: Bool, data: T): ValidTagged[T] = {
      val v = Wire(ValidTagged(chiselTypeOf(data)))
      v.valid := valid
      v.data := data
      v
    }
  }

  class ValidTagged[T <: Data](typeGen: => T) extends Bundle {
    val valid = Bool()
    val data = typeGen
  }


}
