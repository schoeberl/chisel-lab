import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class DisplayTest(dut: Display) extends PeekPokeTester(dut) {
  step(100)
}

class DisplaySpec extends FlatSpec with Matchers {
  "DisplayTest " should "pass" in {
    chisel3.iotesters.Driver.execute(Array("--generate-vcd-output", "on"), () => new Display)
    { c => new DisplayTest(c)} should be (true)
  }
}
