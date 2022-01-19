
/*
 * Test a 3-bit majority circuit.
 */

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class MajoritySimple extends AnyFlatSpec with ChiselScalatestTester {
  "Majority simple test" should "pass" in {
    test(new Majority) { dut =>
      // Simple tests
      dut.io.a.poke(false.B)
      dut.io.b.poke(false.B)
      dut.io.c.poke(false.B)
      dut.clock.step(1)
      dut.io.out.expect(false.B)
      dut.io.a.poke(true.B)
      dut.io.b.poke(true.B)
      dut.io.c.poke(true.B)
      dut.clock.step(1)
      dut.io.out.expect(true.B)
    }
  }
}

class MajorityPrinter extends AnyFlatSpec with ChiselScalatestTester {
  "Majority print results" should "pass" in {
    test(new Majority) { dut =>
      println("Logic table for Majority")
      println("  a     b     c   -> out")
      for (i <- 0 to 7) {
        val a = (i & 1) == 1
        val b = ((i & 2) >> 1) == 1
        val c = ((i & 4) >> 2) == 1
        dut.io.a.poke(a.B)
        dut.io.b.poke(b.B)
        dut.io.c.poke(c.B)
        dut.clock.step(1)
        val out = dut.io.out.peek.litToBoolean
        println(s"$a $b $c -> $out")
      }
    }
  }
}

class MajorityFull extends AnyFlatSpec with ChiselScalatestTester {
  "Majority exhaustive test" should "pass" in {
    test(new Majority) { dut =>
      // Exhaustive testing
      for (i <- 0 to 7) {
        val a = i & 1
        val b = (i & 2) >> 1
        val c = (i & 4) >> 2
        val res = a+b+c > 1
        dut.io.a.poke((a == 1).B)
        dut.io.b.poke((b == 1).B)
        dut.io.c.poke((c == 1).B)
        dut.clock.step(1)
        dut.io.out.expect(res.B)
      }
    }
  }
}