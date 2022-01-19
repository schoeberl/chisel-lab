import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class DecoderSpec extends AnyFlatSpec with ChiselScalatestTester {
  "Decoder" should "pass" in {
    test(new Decoder) { dut =>
      for (n <- 0 to 3) {
        dut.io.sel.poke(n.U)
        dut.clock.step(1)
        val res = 1 << n
        println(n + " " + res)
        dut.io.out.expect(res.U)
      }
    }
  }
}
