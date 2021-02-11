import chisel3.iotesters.PeekPokeTester
import org.scalatest._

// Just have a simple Mux2 test here as a reference
class Mux2Test(dut: Mux2) extends PeekPokeTester(dut) {

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

class Mux2Spec extends FlatSpec with Matchers {
  "Mux2 " should "pass" in {
    chisel3.iotesters.Driver(() => new Mux2) { c => new Mux2Test(c)} should be (true)
  }
}
