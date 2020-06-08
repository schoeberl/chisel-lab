import scala.swing._
import scala.swing.event._

import java.awt.{Color, Graphics2D}

import chisel3.iotesters.PeekPokeTester


/**
  * Simulation of the Basys3 board for the Vending Machine
  */
class VendingSimulation extends MainFrame {

  val sevenSeg = Array(List(23, 5, 40, 10), List(66, 10, 10, 50), List(66, 63, 10, 50),
    List(23, 108, 40, 10), List(10, 63, 10, 50),
    List(10, 10, 10, 50), List(23, 56, 40, 10))

  val digits = Array(0x00, 0x00, 0x00, 0x00)
  var ledVal = new Array[Boolean](16)
  val switches = new Array[CheckBox](16)
  val btn = new Array[ToggleButton](3)
  var btnVal = new Array[Boolean](3)
  var running = true

  def draw7(g: Graphics2D, x: Int, y: Int, seg: Int): Unit = {
    var shift = seg
    for (seg <- sevenSeg) {
      if ((shift & 0x01) != 0) {
        g.setColor(Color.red)
      } else {
        g.setColor(Color.gray)
      }
      shift >>= 1
      g.fillRect(seg(0) + x, seg(1) + y, seg(2), seg(3))
    }
  }

  def drawLed(g: Graphics2D, on: Boolean): Unit = {
    if (on) {
      g.setColor(Color.green)
    } else {
    }
    g.fillRect(5, 5, 15, 8)
  }

  title = "Basys3 Simulator"
  preferredSize = new Dimension(400, 600)
  contents = new GridPanel(3, 1) {
    hGap = 50
    vGap = 50

    contents += new Panel {
      override def paintComponent(g: Graphics2D): Unit = {
        g.setColor(Color.black)
        g.fillRect(10, 10, 380, 143)

        val xOff = 20
        val yOff = 20
        for (i <- 0 until 4) {
          draw7(g, xOff + i*90, yOff, digits(i))
        }
      }
    }

    contents += new GridPanel(2, 16) {
      hGap = 30
      vGap = 30

      btn(0) = new ToggleButton {
        text = "2 kr."
        reactions += {
          case ButtonClicked(_) => {
            btnVal(0) = this.selected
            println("2 kr. " + (if (btnVal(0)) "on" else "off"))
          }
        }
      }

      btn(1) = new ToggleButton {
        text = "5 kr."
        reactions += {
          case ButtonClicked(_) => {
            btnVal(1) = this.selected
            println("5 kr. " + (if (btnVal(1)) "on" else "off"))
          }
        }
      }

      btn(2) = new ToggleButton {
        text = "Buy"
        reactions += {
          case ButtonClicked(_) => {
            btnVal(2) = this.selected
            println("Buy " + (if (btnVal(2)) "on" else "off"))
          }
        }
      }

      contents ++= btn
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

    contents += new GridPanel(1, 6) {

      for (i <- 0 until 16) {
        contents += new GridPanel(4, 1) {
          contents += new Panel {}
          contents += new Panel() {
            override def paintComponent(g: Graphics2D): Unit = {
              drawLed(g, ledVal(15-i))
            }

          }
          val c = new CheckBox("")
          switches(15-i) = c
          contents += c
          contents += new Panel {}
        }
      }
    }
  }
}


class VendingDriver(dut: VendingMachine, d: VendingSimulation) extends PeekPokeTester(dut) {

  while (d.running) {
    step(4)
    var an = peek(dut.io.an).toInt
    val seg = peek(dut.io.seg).toInt
    for (i <- 0 until 4) {
      if ((an & 1) == 0) {
        d.digits(3 - i) = ~seg
      }
      an >>= 1
    }

    d.ledVal(15) = peek(dut.io.releaseCan) == 1
    d.ledVal(0) = peek(dut.io.alarm) == 1
    var price = 0
    for (i <- 0 until 5) {
      price <<= 1
      price += (if (d.switches(4-i).selected) 1 else 0)
    }
    poke(dut.io.price, price)
    poke(dut.io.coin2, d.btnVal(0))
    poke(dut.io.coin5, d.btnVal(1))
    poke(dut.io.buy, d.btnVal(2))

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
