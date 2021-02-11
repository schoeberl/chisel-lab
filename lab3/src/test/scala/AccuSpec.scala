import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class AccuTest(dut: Accu) extends PeekPokeTester(dut) {

  poke(dut.io.din, 0)
  poke(dut.io.setZero, 0)
  // reset value
  expect(dut.io.dout, 0)
  step(1)
  expect(dut.io.dout, 0)
  poke(dut.io.din, 7)
  step(1)
  expect(dut.io.dout, 7)
  step(1)
  expect(dut.io.dout, 14)
  poke(dut.io.setZero, 1)
  step(1)
  expect(dut.io.dout, 0)
  poke(dut.io.setZero, 0)
  poke(dut.io.din, 3)
  step(1)
  expect(dut.io.dout, 3)


}

class AccuSpec extends FlatSpec with Matchers {
  "Accu " should "pass" in {
    chisel3.iotesters.Driver(() => new Accu) { c => new AccuTest(c)} should be (true)
  }
}
