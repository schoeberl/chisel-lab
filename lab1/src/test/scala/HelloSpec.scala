import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class HelloSpec extends AnyFlatSpec with ChiselScalatestTester {

  "Hello" should "pass" in {
    test(new Hello()) { dut =>
      var ledStatus = -1
      println("Start the blinking LED")
      dut.clock.setTimeout(0)
      for (i <- 0 until 100) {
        dut.clock.step(10000)
        val ledNow = dut.io.led.peek.litValue.toInt
        val s = if (ledNow == 0) "o" else "*"
        if (ledStatus != ledNow) {
          System.out.println(s)
          ledStatus = ledNow
        }
      }
      println("End the blinking LED")
    }
  }
}

