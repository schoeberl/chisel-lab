import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Mux2Spec extends AnyFlatSpec with ChiselScalatestTester {
  "Mux2 " should "pass" in {
    test(new Mux2) { dut =>
      for (a <- 0 to 1) {
        for (b <- 0 to 1) {
          for (sel <- 0 to 1) {
            dut.io.a.poke((a == 1).B)
            dut.io.b.poke((b == 1).B)
            dut.io.sel.poke((sel == 1).B)
            dut.clock.step(1)
            val res = if (sel == 0) a else b
            // println(a + " " + b + " " + sel + " " + res)
            dut.io.y.expect((res ==1).B)
          }
        }
      }
    }
  }
}
