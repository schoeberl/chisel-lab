import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class AccuSpec extends AnyFlatSpec with ChiselScalatestTester {
  "Accu " should "pass" in {
    test(new Accu) {dut =>
      dut.io.din.poke(0.U)
      dut.io.setZero.poke(false.B)
      // reset value
      dut.io.dout.expect(0.U)
      dut.clock.step(1)
      dut.io.dout.expect(0.U)
      dut.io.din.poke(7.U)
      dut.clock.step(1)
      dut.io.dout.expect(7.U)
      dut.clock.step(1)
      dut.io.dout.expect(14.U)
      dut.io.setZero.poke(true.B)
      dut.clock.step(1)
      dut.io.dout.expect(0.U)
      dut.io.setZero.poke(false.B)
      dut.io.din.poke(3.U)
      dut.clock.step(1)
      dut.io.dout.expect(3.U)
    }
  }
}
