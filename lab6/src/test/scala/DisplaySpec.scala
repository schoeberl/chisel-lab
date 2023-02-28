import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class DisplaySpec extends AnyFlatSpec with ChiselScalatestTester {
  "DisplayTest " should "pass" in {
    test(new Display(20)).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.io.sw.poke(0x1234.U)
      dut.clock.step(200)
    }
  }
}
