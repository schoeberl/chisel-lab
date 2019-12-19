import chisel3.iotesters.PeekPokeTester
import chisel3.iotesters.Driver
import org.scalatest._


class DisplayTest extends FlatSpec with Matchers {
  "Display test" should "pass" in {
    Driver.execute(Array("--generate-vcd-output", "on"), () => new Display()) {
      c => new PeekPokeTester(c) {
        println("We are generting a VCD file for the display test")
        poke(c.io.sw, 0xf123)
        step(100)
      }
    } should be (true)
  }
}