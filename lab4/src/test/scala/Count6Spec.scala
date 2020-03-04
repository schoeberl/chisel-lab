import chisel3.iotesters._
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


class Count6Wave(dut: Count6) extends PeekPokeTester(dut) {
  step(20)
}

class Count6WaveSpec extends FlatSpec with Matchers {
  "CountWave6 " should "pass" in {
    chisel3.iotesters.Driver.execute(Array("--generate-vcd-output", "on"),() => new Count6) { c => new Count6Wave(c)} should be (true)
  }
}