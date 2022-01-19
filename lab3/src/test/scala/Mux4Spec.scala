import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Mux4Spec extends AnyFlatSpec with ChiselScalatestTester {
  "Mux4 " should "pass" in {
    test(new Mux4) { dut =>
      for (n <- 0 to 15) {
        for (sel <- 0 to 3) {
          dut.io.a.poke((n & 0x1).U)
          dut.io.b.poke(((n >> 1) & 0x1).U)
          dut.io.c.poke(((n >> 2) & 0x1).U)
          dut.io.d.poke(((n >> 3) & 0x1).U)
          dut.io.sel.poke(sel.U)
          dut.clock.step(1)

          val res = (n >> sel) & 0x1

          // println(n + " " + sel + " " + res)
          dut.io.y.expect(res.U)
        }
      }
    }
  }
}
