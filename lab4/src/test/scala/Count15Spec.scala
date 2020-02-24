import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class Count15Test(dut: Count15) extends PeekPokeTester(dut) {

  expect(dut.io.dout, 0)
  for (n <- 0 to 40) {
    expect(dut.io.dout, n & 0xf)
    step(1)
  }

}

class Count15Spec extends FlatSpec with Matchers {
  "Count15 " should "pass" in {
    chisel3.iotesters.Driver(() => new Count15) { c => new Count15Test(c)} should be (true)
  }
}
