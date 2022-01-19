import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Count6Spec extends AnyFlatSpec with ChiselScalatestTester {
  "Count6 " should "pass" in {
    test(new Count6) { dut =>
      dut.io.dout.expect(0.U)
      for (n <- 0 to 40) {
        dut.io.dout.expect((n % 7).U)
        dut.clock.step(1)
      }
    }
  }
}

class Count6WaveSpec extends AnyFlatSpec with ChiselScalatestTester {
  "CountWave6 " should "pass" in {
    test(new Count6).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      dut.clock.step(20)
    }
  }
}