# Digital Electronics 2 FAQ

## Chisel

### Extract bitfield
You can extract a subset of wires from a bus x like this:  
    
    x(n, m)

This will extract wire n down to m both included. It is important to know that n >= m, otherwise Chisel will throw a hard to understand error.

### Missing ```.W```

One possible error when defining constants with a dedicated width is missing the ```.W```
specifier for a width. E.g., ```1.U(32)``` will not define a 32-bit wide constant representing 1.
Instead, the expression ```(32)``` is interpreted as bit extraction from position 32, which results
in a single bit constant of 0. Probably not what the original intention of the programmer was.


## Error msgs


### firrtl.passes.CheckFlows$WrongFlow:  @[cmdx.sc xx:xx]: [module xxxx]  Expression _T is used as a SinkFlow but can only be used as a SourceFlow.

You can't bind some wires of a output bus. Either none or all in one expression. A workaround is to declare a bus where you can bind part of the wires. Then bind this bus to the output bus

### scala.MatchError: List(UInt\<x\>(0) ... UInt\<x>(y)) (of class scala.collection.immutable.$colon$colon)
This error message usually indicates that you have declared an Enum with a wrong indication of number of elements.

### Enum

A wrong number n in Enum(n) for FSM states can give a long error log.

## Vivado

### Error "Bitstream Generation failed"

comes from a missing pin assignment in the .xdc file


