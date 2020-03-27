
import scala.swing._
import scala.swing.event._

import java.awt.{Color, Graphics2D, Point, geom}

/**
  * Work-in-progress: shall become a simulation of the Basys3 display
  */
class DisplaySimulation extends MainFrame {

  val sevenSeg = Array(List(23, 5, 40, 10), List(66, 10, 10, 50), List(66, 63, 10, 50),
    List(23, 108, 40, 10), List(10, 63, 10, 50),
    List(10, 10, 10, 50), List(23, 56, 40, 10))


  val digits = Array(true, true, false, true, true, false, true)


  title = "Display Simulator - Work in Progress"
  preferredSize = new Dimension(400, 600)
  // contents = new Label("Here is the contents!")
  contents = new GridPanel(3, 2) {
    hGap = 10
    vGap = 10
    contents += new Button {
      text = "Press Me!"
      reactions += {
        case ButtonClicked(_) => text = "Hello Chisel!"
      }
    }



    contents += new Panel {
      background = Color.white
      var path = new geom.GeneralPath
      path.moveTo(0, 0)
      path.lineTo(100, 100)
      repaint()
      override def paintComponent(g: Graphics2D): Unit = {
        g.drawString("Press left mouse button and drag to paint.", 10, 26)
        g.setColor(Color.black)
        g.draw(path)
        g.setColor(Color.red)
        // g.fillPolygon(Array(10, 20, 40), Array(60, 30, 10), 3)
        //g.fillRect(10, 20, 3, 10)
        //g.setColor(Color.blue)
        //g.fillRect(30, 30, 3, 10)
        println("repaint")
        var cnt = 0
        for (seg <- sevenSeg) {
          if (digits(cnt)) {
            g.setColor(Color.red)
          } else {
            g.setColor(Color.black)

          }
          cnt += 1
          g.fillRect(seg(0), seg(1), seg(2), seg(3))
        }
      }
    }
    contents += new Label("Hallo Label")
    // contents += new ToggleButton { text = "Toggle" }

  }
}

object DisplaySimulation extends App {

  val ui = new DisplaySimulation
  ui.visible = true
  println("End of main function")
}
