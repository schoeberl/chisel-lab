import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Count15Spec extends AnyFlatSpec with ChiselScalatestTester {
  "Count15 " should "pass" in {
    test(new Count15) { dut =>
      dut.io.dout.expect(0.U)
      for (n <- 0 to 40) {
        dut.io.dout.expect((n & 0xf).U)
        dut.clock.step(1)
      }
    }
  }
}
