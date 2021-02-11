import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class DelayTest(dut: Delay) extends PeekPokeTester(dut) {

  poke(dut.io.din, 0)
  step(5)
  expect(dut.io.dout, 0)
  poke(dut.io.din, 123)
  step(1)
  expect(dut.io.dout, 0)
  step(1)
  expect(dut.io.dout, 123)
  poke(dut.io.din, 0)
  step(1)
  expect(dut.io.dout, 123)
  step(1)
  expect(dut.io.dout, 0)

}

class DelaySpec extends FlatSpec with Matchers {
  "Delay " should "pass" in {
    chisel3.iotesters.Driver(() => new Delay) { c => new DelayTest(c)} should be (true)
  }
}
