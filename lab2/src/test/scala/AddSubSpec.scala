import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class AddSubTester(dut: AddSub) extends PeekPokeTester(dut) {

  poke(dut.io.a, 1)
  poke(dut.io.b, 2)
  poke(dut.io.selAdd, 1)
  step(1)
  expect(dut.io.y, 3)

  poke(dut.io.a, 3)
  poke(dut.io.b, 2)
  poke(dut.io.selAdd, 0)
  step(1)
  expect(dut.io.y, 1)
}

class AddSubSpec extends FlatSpec with Matchers {
  "AddSub" should "pass" in {
    chisel3.iotesters.Driver(() => new AddSub) { c => new AddSubTester(c)} should be (true)
  }
}
