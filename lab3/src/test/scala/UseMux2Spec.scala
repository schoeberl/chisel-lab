import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class UseMux2Test(dut: UseMux2) extends PeekPokeTester(dut) {

  poke(dut.io.sel, 0)
  step(1)
  expect(dut.io.dout, 1)
  poke(dut.io.sel, 1)
  step(1)
  expect(dut.io.dout, 0)
}

class UseMux2Spec extends FlatSpec with Matchers {
  "UseMux2 " should "pass" in {
    chisel3.iotesters.Driver(() => new UseMux2) { c => new UseMux2Test(c)} should be (true)
  }
}
