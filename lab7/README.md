# Lab 7: Using an External Component

In this lab you will use an external component from a Chisel library hosted
at Maven central. This shows you how open-source HW components can be shared
easily with Chisel.

In this lab you will add
a [UART](https://en.wikipedia.org/wiki/Universal_asynchronous_receiver-transmitter)
component to your design. The UART is a
serial interface where you can send and receive characters between the FPGA
board and a terminal program on your computer. The UART is a standard
component in many FPGA boards and is used for debugging and for communication.

## Preparation

Do have some feedback that your setup in the FPGA is working
add a blinking LED to your  design.
You can use the code from the previous lab1.

## Using an External Component

The UART component is available as a Chisel library 
[ip-contributions](https://github.com/freechipsproject/ip-contributions)
from Maven central. You
can add it to your project with the following lines in your `build.sbt` file:

```scala
libraryDependencies += "edu.berkeley.cs" % "ip-contributions" % "0.5.1"
```

The UART component is available in the package ```package chisel.lib.uart```.
Therefore, include the following line in your source file:

```scala
import chisel.lib.uart._
```

The UART component is a Chisel module. You can instantiate it with the
following code:

```scala   
val uart = Module(new BufferedTx(115200, 100000000))
```

The first parameter is the baud rate, the second parameter is the clock
frequency. The ```BufferedTx``` component has a read/valid interface to
send characters to the UART.

To see the output of the UART you need to connect it to a terminal program.
Under Winows PuTTY is a good choice. Under Linux you can use the ```gtkterm```.
Under Mac OS X you can use the ```screen``` command.

Set the baud rate of the terminal program to 115200 and one stop bit,
no parity, no flow control. The UART sends the characters as ASCII
characters. Therefore, you can see the characters in the terminal program.

### Sending Characters without Handshaking

As the initial exercise send alternating '0' and '1' characters to the
UART at the blink frequency of your LED. As this is way slower than the
UART baud rate, you can ignore handshaking for now.

The following code sends the character '0'  to the UART:

```scala
uart.io.channel.valid := true.B
uart.io.channel.bits := '0'.U
```

Note that the data is not the binary zero, but needs ot be encoded in
[ASCII](https://en.wikipedia.org/wiki/ASCII). The character '0' is
encoded as 48 in ASCII. However, in Chisel you can simply write '0' as
character literal. The character literal is automatically converted to
the corresponding ASCII code.

However, if you keep valid ```true.B``` for more than one clock cycle,
the UART will send the same character multiple times. Therefore, you
shall have the valid signal set only for a single clock cycle.

### Send a String

Now, send the string "Hello World!" to the UART. You can use a ```Vec```
set to a default value with a ```VecInit``` to store the characters of the string. The following
code creates a ```Vec``` with the characters of the string "Hello World!".

```scala
val hello = VecInit('H'.U, 'e'.U, 'l'.U, ...)
```

Use a counter to send the characters of the string one by one.
Note that your components needs to consider now handshaking.
Only when the when ```ready``` is ```true.B``` you can send the next
character.



