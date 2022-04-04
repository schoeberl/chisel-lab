import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class VendingTester extends AnyFlatSpec with ChiselScalatestTester {
  "Vending machine test" should "pass" in {
    test(new VendingMachine(20)).withAnnotations(Seq(WriteVcdAnnotation, VerilatorBackendAnnotation)) { c =>

      def pay2 = {
        c.io.coin2.poke(true.B)
        c.clock.step(5)
        c.io.coin2.poke(false.B)
        c.clock.step(4)
      }

      c.io.price.poke(7.U)
      pay2
      // continue here
      // Should we be able to buy a soda now?
    }
  }
}