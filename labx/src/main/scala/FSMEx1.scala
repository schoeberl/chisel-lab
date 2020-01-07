package exercises

import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class UpDownFSM1() extends Module {
    val io = IO(new Bundle {
        val up      = Input(Bool())
        val down    = Input(Bool())
        val zero    = Input(Bool())
        
        val out_num = Output(UInt(4.W))
    })
    io.out_num := 0.U
    // Implement the FSM in two sections, next state logic and datapath.
}

class FSMTop1 extends Module {
  val io = IO(new Bundle {
    val sw = Input(UInt(16.W)) //16 switches
    val led = Output(UInt(16.W)) //16 LEDs
      
    val seg = Output(UInt(7.W))
    val seg_an = Output(UInt(4.W))
      
    val up = Input(Bits(1.W)) //These 4 signals are meant for the 4 push-buttons (T18) 
    val down = Input(Bits(1.W)) //(U17)
    val left = Input(Bits(1.W)) //(W19)
    val right = Input(Bits(1.W)) //(T17)
  })

  //Instantiate the FSM module and connect unused outputs to static value

}

object FSMTop1Driver extends App {
    chisel3.Driver.execute(Array("--target-dir", "generated", "-tn", "fsm_top"), () => new FSMTop1())
}