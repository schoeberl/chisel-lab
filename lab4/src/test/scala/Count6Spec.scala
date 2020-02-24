import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class Count6Test(dut: Count6) extends PeekPokeTester(dut) {

  expect(dut.io.dout, 0)
  for (n <- 0 to 40) {
    expect(dut.io.dout, n % 7)
    step(1)
  }

}

class Count6Spec extends FlatSpec with Matchers {
  "Count6 " should "pass" in {
    chisel3.iotesters.Driver(() => new Count6) { c => new Count6Test(c)} should be (true)
  }
}
