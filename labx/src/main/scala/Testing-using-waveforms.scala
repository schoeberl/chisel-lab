import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class PizzaOrdering() extends Module {
    val io = IO(new Bundle {
        val input      = Input(Bool())
        val pickUp     = Input(Bool())
        
        val startMsg   = Output(Bool())
        val succesLED  = Output(Bool())
        val alarmLED   = Output(Bool())
    })
    
    val idle :: setNumber :: call :: respond :: success :: alarm :: Nil = Enum(6)
    
    val stateReg = RegInit(idle)
    
    val waitCntReg = RegInit(15.U)
    
    val number = Wire(UInt(32.W))
    
    
    
    switch(stateReg){
        is (idle) {
            when(io.input){
                stateReg := setNumber
            }
        }
        is (setNumber) {
            stateReg := call
        }
        is (call) {
            when(io.pickUp){
                stateReg := respond
            }.elsewhen (waitCntReg === 0.U){
                stateReg := success
            }
            
        }
        is (respond) {
            stateReg := success
        }
        is (success) {
            when(io.input){
                stateReg := idle
            }
        }
        is (alarm) {
            when(io.input){
                stateReg := idle
            }
        }
    }
    
    number       := 0.U
    io.startMsg  := false.B
    io.succesLED := false.B
    io.alarmLED  := false.B
    
    switch(stateReg){
        is (idle) {
            waitCntReg := 15.U
        }
        is (setNumber) {
            number := Cat(4.U(4.W), 5.U(4.W), 8.U(4.W), 7.U(4.W), 0.U(4.W), 6.U(4.W), 1.U(4.W), 6.U(4.W))
        }
        is (call) {
            when(waitCntReg =/= 0.U){
                waitCntReg := waitCntReg - 1.U
            }
        }
        is (respond) {
            io.startMsg := true.B
        }
        is (success){
            io.succesLED := true.B
        }
        is (alarm){
            io.alarmLED := true.B
        }
    }
}

object testingUsingWaveforms extends App {
	val testResult = Driver.execute(Array("--fint-write-vcd","on", "-tn", "Pizza","--target-dir", "generated"),() => new PizzaOrdering()) {
	  c => new PeekPokeTester(c) {
		step(3) //Move 50 clock cycle ahead.
		poke(c.io.input, true)
		step(1)
		poke(c.io.input, false.B)
		step(20)
		poke(c.io.pickUp, true.B)
		step(1)
		poke(c.io.pickUp, false.B)
		step(15)
	  }
	}
}
