import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class UseMux2Spec extends AnyFlatSpec with ChiselScalatestTester {
  "UseMux2 " should "pass" in {
    test(new UseMux2) { dut =>
      dut.io.sel.poke(0.U)
      dut.clock.step(1)
      dut.io.dout.expect(1.U)
      dut.io.sel.poke(1.U)
      dut.clock.step(1)
      dut.io.dout.expect(0.U)
    }
  }
}
