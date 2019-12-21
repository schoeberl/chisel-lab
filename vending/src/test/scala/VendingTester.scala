import chisel3.iotesters.PeekPokeTester
import chisel3.iotesters.Driver
import org.scalatest._


class VendingTester extends FlatSpec with Matchers {
  "Vending machine test" should "pass" in {
    Driver.execute(Array("--generate-vcd-output", "on"), () => new VendingMachine()) {
      c => new PeekPokeTester(c) {
        println("We are generting a VCD file with the test of the vending machine")
        poke(c.io.price, 7)
        step(3)
        poke(c.io.coin2, 1)
        step(3)
        poke(c.io.coin2, 0)
        step(6)
        poke(c.io.coin5, 1)
        step(3)
        poke(c.io.coin5, 0)
        step(8)
        poke(c.io.buy, 1)
        step(3)
        poke(c.io.buy, 0)
        step(10)
      }
    } should be (true)
  }
}