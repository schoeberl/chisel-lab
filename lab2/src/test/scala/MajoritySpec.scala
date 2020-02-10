
/*
 * Test a 3-bit majority circuit.
 */

import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class MajorityTesterSimple(dut: Majority) extends PeekPokeTester(dut) {

  // Simple tests
  poke(dut.io.a, 0)
  poke(dut.io.b, 0)
  poke(dut.io.c, 0)
  step(1)
  expect(dut.io.out, 0)
  poke(dut.io.a, 1)
  poke(dut.io.b, 1)
  poke(dut.io.c, 1)
  step(1)
  expect(dut.io.out, 1)
}

class MajoritySimple extends FlatSpec with Matchers {
  "Majority simple test" should "pass" in {
    chisel3.iotesters.Driver(() => new Majority) { c => new MajorityTesterSimple(c)} should be (true)
  }
}

class MajorityTesterPrint(dut: Majority) extends PeekPokeTester(dut) {

  println("Logic table for Majority")
  println("a b c -> out")
  for (i <- 0 to 7) {
    val a = i & 1
    val b = (i & 2) >> 1
    val c = (i & 4) >> 2
    poke(dut.io.a, a)
    poke(dut.io.b, b)
    poke(dut.io.c, c)
    step(1)
    val out = peek(dut.io.out)
    println(s"$a $b $c -> $out")
  }
}

class MajorityPrinter extends FlatSpec with Matchers {
  "Majority print results" should "pass" in {
    chisel3.iotesters.Driver(() => new Majority) { c => new MajorityTesterPrint(c)} should be (true)
  }
}

class MajorityTester(dut: Majority) extends PeekPokeTester(dut) {

  // Exhaustive testing
  for (i <- 0 to 7) {
    val a = i & 1
    val b = (i & 2) >> 1
    val c = (i & 4) >> 2
    val res = if (a+b+c > 1) 1 else 0
    poke(dut.io.a, a)
    poke(dut.io.b, b)
    poke(dut.io.c, c)
    step(1)
    expect(dut.io.out, res)
  }
}

class MajorityFull extends FlatSpec with Matchers {
  "Majority exhaustive test" should "pass" in {
    chisel3.iotesters.Driver(() => new Majority) { c => new MajorityTester(c)} should be (true)
  }
}