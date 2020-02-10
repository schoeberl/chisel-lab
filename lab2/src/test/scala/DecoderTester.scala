import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class DecoderTester(dut: Decoder) extends PeekPokeTester(dut) {

  for (n <- 0 to 3) {
    poke(dut.io.sel, n)
    step(1)
    val res = 1 << n
    println(n + " " + res)
    expect(dut.io.out, res)
  }
}

class DecoderSpec extends FlatSpec with Matchers {
  "Decoder" should "pass" in {
    chisel3.iotesters.Driver(() => new Decoder) { c => new DecoderTester(c)} should be (true)
  }
}
