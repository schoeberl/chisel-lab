import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Mux5Test extends AnyFlatSpec with ChiselScalatestTester {
  "Mux5 " should "pass" in {
    test(new Mux5()) { dut =>
      // write your test here
    }
  }
}
