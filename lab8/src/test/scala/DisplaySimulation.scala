import scala.swing._
import scala.swing.event._


class DisplaySimulation extends MainFrame {
  title = "Display Simulator - Work in Progress"
  preferredSize = new Dimension(320, 240)
  // contents = new Label("Here is the contents!")
  contents = new GridPanel(2, 3) {
    hGap = 10
    vGap = 10
    contents += new Button {
      text = "Press Me!"
      reactions += {
        case ButtonClicked(_) => text = "Hello Chisel!"
      }
    }
    contents += new Label("Hallo Label")
  }
}

object DisplaySimulation extends App {

  val ui = new DisplaySimulation
  ui.visible = true
  println("End of main function")
}
