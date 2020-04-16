import scala.swing._
import scala.swing.event._

import java.awt.{Color, Graphics2D}

import chisel3.iotesters.PeekPokeTester


/**
  * Work-in-progress: shall become a simulation of the Basys3 display
  */
class VendingSimulation extends MainFrame {

  val sevenSeg = Array(List(23, 5, 40, 10), List(66, 10, 10, 50), List(66, 63, 10, 50),
    List(23, 108, 40, 10), List(10, 63, 10, 50),
    List(10, 10, 10, 50), List(23, 56, 40, 10))

  val digits = Array(0x00, 0x00, 0x00, 0x00)
  var inVal = 0x1234
  var running = true

  def draw7(g: Graphics2D, x: Int, y: Int, seg: Int): Unit = {
    var shift = seg
    for (seg <- sevenSeg) {
      if ((shift & 0x01) != 0) {
        g.setColor(Color.red)
      } else {
        g.setColor(Color.black)
      }
      shift >>= 1
      g.fillRect(seg(0) + x, seg(1) + y, seg(2), seg(3))
    }
  }

  title = "Basys3 Simulator"
  preferredSize = new Dimension(400, 400)
  // contents = new Label("Here is the contents!")
  contents = new GridPanel(2, 1) {
    hGap = 50
    vGap = 50

    contents += new Panel {
      override def paintComponent(g: Graphics2D): Unit = {
        val xOff = 20
        val yOff = 20
        for (i <- 0 until 4) {
          draw7(g, xOff + i*90, yOff, digits(i))
        }
      }
    }

    contents += new GridPanel(3, 2) {
      hGap = 30
      vGap = 30

      val label = new Label("This is work in progress")
      contents += label
      contents += new Panel {}


      contents += new Button {
        text = "2 kr."
        reactions += {
          case ButtonClicked(_) => {
            println("2 kr. clicked")
          }
        }
      }

      contents += new Button {
        text = "5 kr."
        reactions += {
          case ButtonClicked(_) => {
            println("5 kr. clicked")
          }
        }
      }

      contents += new Button {
        text = "Buy"
        reactions += {
          case ButtonClicked(_) => {
            println("Buy clicked")
          }
        }
      }

      contents += new Panel {}

      contents += new Button {
        text = "Exit"
        reactions += {
          case ButtonClicked(_) => {
            running = false
            closeOperation()
          }
        }
      }
    }
  }
}


class VendingDriver(dut: VendingMachine, d: VendingSimulation) extends PeekPokeTester(dut) {

  while (d.running) {
    // poke(dut.io.sw, d.inVal)
    step(4)
    var an = peek(dut.io.an).toInt
    val seg = peek(dut.io.seg).toInt
    for (i <- 0 until 4) {
      if ((an & 1) == 0) {
        d.digits(3 - i) = ~seg
      }
      an >>= 1
    }
    d.repaint()
    Thread.sleep(10)
  }

}


object VendingSimulation extends App {

  val display = new VendingSimulation
  display.visible = true

  chisel3.iotesters.Driver(() => new VendingMachine(20))
  { c => new VendingDriver(c, display) }

}
