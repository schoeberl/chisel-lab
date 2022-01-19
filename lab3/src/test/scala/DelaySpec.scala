import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class DelaySpec extends AnyFlatSpec with ChiselScalatestTester {
  "Delay " should "pass" in {
    test(new Delay) { dut =>
      dut.io.din.poke(0.U)
      dut.clock.step(5)
      dut.io.dout.expect(0.U)

      dut.io.din.poke(123.U)
      dut.clock.step(1)
      dut.io.dout.expect(0.U)
      dut.clock.step(1)
      dut.io.dout.expect(123.U)

      dut.io.din.poke(0.U)
      dut.clock.step(1)
      dut.io.dout.expect(123.U)
      dut.clock.step(1)
      dut.io.dout.expect(0.U)
    }
  }
}
