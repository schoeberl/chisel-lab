import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class AddSubTester(dut: AddSub) extends PeekPokeTester(dut) {

  for (a <- 0 to 1) {
    for (b <- 0 to 1) {
      for (sel <- 0 to 1) {
        poke(dut.io.a, a)
        poke(dut.io.b, b)
        poke(dut.io.sel, sel)
        step(1)
        val res = if (sel == 0) a else b
        // println(a + " " + b + " " + sel + " " + res)
        expect(dut.io.y, res)
      }
    }
  }
}

class AddSubSpec extends FlatSpec with Matchers {
  "AddSub" should "pass" in {
    chisel3.iotesters.Driver(() => new AddSub) { c => new AddSubTester(c)} should be (true)
  }
}
