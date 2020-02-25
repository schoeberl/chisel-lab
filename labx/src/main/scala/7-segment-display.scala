//val path = System.getProperty("user.dir") + "/source/load-ivy.sc"
//interp.load.module(ammonite.ops.Path(java.nio.file.FileSystems.getDefault().getPath(path)))

import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

object top{
	class SevenSegDecoder() extends Module {
		
		val io = IO(new Bundle {
		    val in = Input(UInt(4.W))
		    val out = Output(Bits(7.W))
		})
		
		
		//Make an output wire
		
		
		//Connect this wire to different values, depending on the input
		
		
		//Don't forget to connect the output of the module
		io.out := "b1010101".U //dummy output
	}

	def print7Segment(x:BigInt,y: Int){
		println(y.toHexString)                       //Print the hexadecimal value
		println(if ((x & 0x40) == 0) " _"  else " ") //Print top "_"
		print(if((x & 0x2) == 0) "|" else " ")       //Print top left "|"
		print(if((x & 0x1) == 0) "_" else " ")       //Print middle "_"
		println(if((x & 0x20) == 0) "|" else " ")    //Print top right "|"
		print(if((x & 0x4) == 0) "|" else " ")       //Print lower left "|"
		print(if((x & 0x8) == 0) "_" else " ")       //Print lower "_"
		println(if((x & 0x10) == 0) "|" else " ")    //Print lower right "|"
		println()                                    //Print empty line
	}


	def testSevenSegDecoder(){
		Driver.execute(Array(),() => new SevenSegDecoder()) {
		  c => new PeekPokeTester(c) {
			  
			for (value <- 0 until 16) {
				poke(c.io.in, value) //We apply a value to the input
				println(peek(c.io.out).toString(2).reverse.padTo(7,'0').reverse) //And check the value on the output.
				print7Segment(peek(c.io.out),value) //Here we print the result, as it would look on the 7-segment display.
			}
		  }
		}
	}
	class Counter() extends Module {
		val io = IO(new Bundle {
		    val out = Output(UInt(4.W)) //The counter has 4 bits of output.
		})
		
		
		//Describe the counter
		
		
		//And don't forget to connect the output
		io.out := 0.U
	}


	val testResult = Driver.execute(Array(),() => new Counter()) {
	  c => new PeekPokeTester(c) {
		  
		for (value <- 0 until 50) { //We check 50 times.
		    println(peek(c.io.out).toString ) //Print the value on the output.
		    step(1) //Move 1 clock cycle ahead.
		}
	  }
	}

	class BCDTable() extends Module {
		val io = IO(new Bundle {
		    val address = Input(UInt(8.W))
		    val data = Output(UInt(8.W))
		})
		
		val lookup = new Array[Int](256)
		
		
		for (i <- 0 until 99){
		    lookup(i) = ((i/10)<<4) + i % 10
		}
		
		val table = VecInit(lookup.map(_.U(8.W)))
		
		io.data := table(io.address)
	}

	//For implementing the FSMD and PeekPokeTester
	class FSM extends Module {
	  val io = IO(new Bundle {
		//Define any inputs/outputs needed
	  })
		
		
	  //Define the FSM and datapath

	}

	def testFSM(){
		Driver.execute(Array("--generate-vcd-output","on", "-tn", "FSM","--target-dir", "generated"),() => new FSM()) {
		  c => new PeekPokeTester(c) {
			  
		  }
		}
	}
	//This cell can be used to define new modules
	//Otherwise, implement it directly in the top module.


	/**
	 * A top level to wire FPGA buttons, LEDs and 7-segment controllers
	 * to the Basys3 board input and output.
	 */
	class VendingMachineTop extends Module {
	  val io = IO(new Bundle {
		val sw = Input(UInt(16.W)) //16 switches
		val led = Output(UInt(16.W)) //16 LEDs
		  
		val seg = Output(Bits(7.W))
		val seg_an = Output(Bits(4.W))
		  
		val up = Input(Bits(1.W)) //These 4 signals are meant for the 4 push-buttons (T18) 
		val down = Input(Bits(1.W)) //(U17)
		val left = Input(Bits(1.W)) //(W19)
		val right = Input(Bits(1.W)) //(T17)
	  })

		
	  //Instantiate the SevenSegDecoder module
		
		
		
	  //And other hardware as needed in the next steps
		
		
	  //And don't forget to connect the outputs
	  io.led := 42.U
	  io.seg := 42.U
	  io.seg_an := "b0101".U
	}


		                
		                                 
	}
object SevenSegmentDriver extends App {
	//Print output of sevenseg decoder
	top.testSevenSegDecoder()

	//Generate waveform
	Driver.execute(Array("--fint-write-vcd","on", "-tn", "VendingMachineTop","--target-dir", "generated"),() => new top.VendingMachineTop()) {
	  c => new PeekPokeTester(c) {
		  step(1)
	  }
	}
	
	//Generate hardware
    chisel3.Driver.execute(Array("--target-dir", "generated", "-tn", "vending_machine"), 
                                     () => new top.VendingMachineTop())
}
