import chisel3._
import chisel3.util._

import scala.swing._
import scala.swing.event._

class UI extends MainFrame {
  title = "Display Simulator"
  preferredSize = new Dimension(320, 240)
  // contents = new Label("Here is the contents!")
  contents = new GridPanel(2, 3) {
    hGap = 10
    vGap = 10
    contents += new Button {
      text = "Press Me!"
      reactions += {
        case ButtonClicked(_) => text = "Hello Scala"
      }
    }
    contents += new Label("Hallo Label")
  }
}



class Display extends Module {
  val io = IO(new Bundle {
    val seg = Output(UInt(7.W))
    val an = Output(UInt(4.W))
  })

  val sevSeg = WireInit("b1111111".U(7.W))

  // *** your code starts here


  // *** your code ends here

  io.seg := ~sevSeg
  io.an := "b1110".U
}

// generate Verilog
object Display extends App {
  println("Hello")
  // chisel3.Driver.execute(args, () => new Display())

  val ui = new UI
  ui.visible = true
  println("End of main function")
}