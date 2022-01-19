import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class AddSubSpec extends AnyFlatSpec with ChiselScalatestTester {
  "AddSub" should "pass" in {

    test(new AddSub) { dut =>
      dut.io.a.poke(1.U)
      dut.io.b.poke(2.U)
      dut.io.selAdd.poke(true.B)
      dut.clock.step()
      dut.io.y.expect(3.U)
      dut.io.a.poke(3.U)
      dut.io.b.poke(2.U)
      dut.io.selAdd.poke(false.B)
      dut.clock.step()
      dut.io.y.expect(1.U)
    }
  }
}
