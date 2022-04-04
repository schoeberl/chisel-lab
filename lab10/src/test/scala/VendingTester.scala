import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec


class VendingTester extends AnyFlatSpec with ChiselScalatestTester {
  "Vending machine test" should "pass" in {
    test(new VendingMachine(20)).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      println("We are generting a VCD file with the test of the vending machine")
      dut.io.price.poke(7.U)
      dut.clock.step(3)
      dut.io.coin2.poke(true.B)
      dut.clock.step(3)
      dut.io.coin2.poke(false.B)
      dut.clock.step(6)
      dut.io.coin5.poke(true.B)
      dut.clock.step(3)
      dut.io.coin5.poke(false.B)
      dut.clock.step(8)
      dut.io.buy.poke(true.B)
      dut.clock.step(3)
      dut.io.buy.poke(false.B)
      dut.clock.step(10)
    }
  }
}