import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

// Just have a simple Mux2 test here as a reference
class Mux2Spec extends AnyFlatSpec with ChiselScalatestTester {
  "Mux2 " should "pass" in {
    test(new Mux2) { dut =>
      for (a <- 0 to 1) {
        for (b <- 0 to 1) {
          for (sel <- 0 to 1) {
            dut.io.a.poke(a.U)
            dut.io.b.poke(b.U)
            dut.io.sel.poke(sel.U)
            dut.clock.step(1)
            val res = if (sel == 0) a else b
            // println(a + " " + b + " " + sel + " " + res)
            dut.io.y.expect(res.U)
          }
        }
      }
    }
  }
}
