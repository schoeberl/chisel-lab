import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class SevenSegCountSpec extends AnyFlatSpec with ChiselScalatestTester {
	"SevenSegTest " should "pass" in {
		test(new CountSevenSeg).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
			dut.clock.step(100)
		}
	}
}
