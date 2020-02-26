import chisel3.iotesters.PeekPokeTester
import org.scalatest._

class SevenSegDecoderTester(dut: SevenSegDecoder) extends PeekPokeTester(dut) {
	def print7Segment(x:BigInt,y: Int){
		var tempStr = ""
    println(y.toHexString)                       //Print the hexadecimal value
    println(if ((~x & 0x01) != 0) " _"  else " ") //Print top "_"
		tempStr += (if((~x & 0x20) != 0) "|" else " ") //Print top left "|"
		tempStr += (if((~x & 0x40) != 0) "_" else " ") //Print middle "_"
		tempStr += (if((~x & 0x2) != 0) "|" else " ")//Print top right "|"
		println(tempStr)
    tempStr = (if((~x & 0x10) != 0) "|" else " ")  //Print lower left "|"
    tempStr += (if((~x & 0x8) != 0) "_" else " ") //Print lower "_"
		tempStr += (if((~x & 0x4) != 0) "|" else " ")//Print lower right "|"
		println(tempStr)
    println()                                    //Print empty line
	}
	  
  for (value <- 0 until 16) {
		poke(dut.io.sw, value) //We apply a value to the input
		println(peek(dut.io.seg).toString(2).reverse.padTo(7,'0').reverse) //And check the value on the output.
		print7Segment(peek(dut.io.seg),value) //Here we print the result, as it would look on the 7-segment display.
	}
}

class SevenSegDecoderSpec extends FlatSpec with Matchers {
  "SevenSegDecoder " should "pass" in {
    chisel3.iotesters.Driver(() => new SevenSegDecoder) { c => new SevenSegDecoderTester(c)} should be (true)
  }
}
