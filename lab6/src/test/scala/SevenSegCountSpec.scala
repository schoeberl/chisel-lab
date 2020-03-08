import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class SevenSegTest(dut: CountSevenSeg) extends PeekPokeTester(dut) {
  step(100)
}

class SevenSegCountSpec extends FlatSpec with Matchers {
	"SevenSegTest " should "pass" in {
		chisel3.iotesters.Driver.execute(Array("--generate-vcd-output", "on"), () => new CountSevenSeg)
		{ c => new SevenSegTest(c)} should be (true)
	}
}
