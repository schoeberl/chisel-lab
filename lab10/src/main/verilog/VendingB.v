module VendingMachine(
  input        clock,
  input        reset,
  input  [4:0] io_price,
  input        io_coin2,
  input        io_coin5,
  input        io_buy,
  output       io_releaseCan,
  output       io_alarm,
  output [6:0] io_seg,
  output [3:0] io_an
);
`ifdef RANDOMIZE_REG_INIT
  reg [31:0] _RAND_0;
  reg [31:0] _RAND_1;
  reg [31:0] _RAND_2;
  reg [31:0] _RAND_3;
  reg [31:0] _RAND_4;
  reg [31:0] _RAND_5;
  reg [31:0] _RAND_6;
  reg [31:0] _RAND_7;
  reg [31:0] _RAND_8;
  reg [31:0] _RAND_9;
  reg [31:0] _RAND_10;
`endif // RANDOMIZE_REG_INIT
  reg  coin2_REG; // @[VendingMachine.scala 16:38]
  reg  coin2_REG_1; // @[VendingMachine.scala 16:30]
  reg  coin2_REG_2; // @[VendingMachine.scala 17:37]
  wire  coin2 = coin2_REG_1 & ~coin2_REG_2; // @[VendingMachine.scala 17:27]
  reg  coin5_REG; // @[VendingMachine.scala 16:38]
  reg  coin5_REG_1; // @[VendingMachine.scala 16:30]
  reg  coin5_REG_2; // @[VendingMachine.scala 17:37]
  wire  coin5 = coin5_REG_1 & ~coin5_REG_2; // @[VendingMachine.scala 17:27]
  reg  buySync_REG; // @[VendingMachine.scala 16:38]
  reg  buySync; // @[VendingMachine.scala 16:30]
  reg  buy_REG; // @[VendingMachine.scala 17:37]
  wire  buy = buySync & ~buy_REG; // @[VendingMachine.scala 17:27]
  wire [7:0] price = {3'h0,io_price}; // @[Cat.scala 31:58]
  reg [7:0] regSum; // @[VendingMachine.scala 37:23]
  reg [2:0] stateReg; // @[VendingMachine.scala 39:25]
  wire  enough = regSum >= price; // @[VendingMachine.scala 41:23]
  wire [2:0] _GEN_0 = coin2 ? 3'h1 : stateReg; // @[VendingMachine.scala 45:19 46:18 39:25]
  wire  _T_1 = 3'h1 == stateReg; // @[VendingMachine.scala 43:21]
  wire  _T_2 = 3'h2 == stateReg; // @[VendingMachine.scala 43:21]
  wire  _T_3 = 3'h3 == stateReg; // @[VendingMachine.scala 43:21]
  wire [2:0] _GEN_4 = ~buySync ? 3'h0 : stateReg; // @[VendingMachine.scala 69:22 39:25 69:33]
  wire [2:0] _GEN_6 = 3'h6 == stateReg ? _GEN_4 : stateReg; // @[VendingMachine.scala 43:21 39:25]
  wire [2:0] _GEN_7 = 3'h5 == stateReg ? 3'h6 : _GEN_6; // @[VendingMachine.scala 43:21 72:16]
  wire [2:0] _GEN_8 = 3'h4 == stateReg ? _GEN_4 : _GEN_7; // @[VendingMachine.scala 43:21]
  wire [2:0] _GEN_9 = 3'h3 == stateReg ? 3'h4 : _GEN_8; // @[VendingMachine.scala 43:21 66:16]
  wire [7:0] _add_T = {3'h0,io_price}; // @[VendingMachine.scala 83:35]
  wire [7:0] _add_T_3 = 8'sh0 - $signed(_add_T); // @[VendingMachine.scala 83:27]
  wire [7:0] _GEN_13 = _T_3 ? $signed(_add_T_3) : $signed(8'sh0); // @[VendingMachine.scala 80:20 83:20]
  wire [7:0] _GEN_14 = _T_2 ? $signed(8'sh3) : $signed(_GEN_13); // @[VendingMachine.scala 80:20 82:21]
  wire [7:0] add = _T_1 ? $signed(8'sh2) : $signed(_GEN_14); // @[VendingMachine.scala 80:20 81:21]
  wire [7:0] _regSum_T_4 = $signed(regSum) + $signed(add); // @[VendingMachine.scala 85:35]
  assign io_releaseCan = stateReg == 3'h4; // @[VendingMachine.scala 87:29]
  assign io_alarm = stateReg == 3'h6; // @[VendingMachine.scala 88:24]
  assign io_seg = 7'h0; // @[VendingMachine.scala 99:10]
  assign io_an = 4'h0; // @[VendingMachine.scala 100:9]
  always @(posedge clock) begin
    coin2_REG <= io_coin2; // @[VendingMachine.scala 16:38]
    coin2_REG_1 <= coin2_REG; // @[VendingMachine.scala 16:30]
    coin2_REG_2 <= coin2_REG_1; // @[VendingMachine.scala 17:37]
    coin5_REG <= io_coin5; // @[VendingMachine.scala 16:38]
    coin5_REG_1 <= coin5_REG; // @[VendingMachine.scala 16:30]
    coin5_REG_2 <= coin5_REG_1; // @[VendingMachine.scala 17:37]
    buySync_REG <= io_buy; // @[VendingMachine.scala 16:38]
    buySync <= buySync_REG; // @[VendingMachine.scala 16:30]
    buy_REG <= buySync; // @[VendingMachine.scala 17:37]
    if (reset) begin // @[VendingMachine.scala 37:23]
      regSum <= 8'h0; // @[VendingMachine.scala 37:23]
    end else begin
      regSum <= _regSum_T_4; // @[VendingMachine.scala 85:10]
    end
    if (reset) begin // @[VendingMachine.scala 39:25]
      stateReg <= 3'h0; // @[VendingMachine.scala 39:25]
    end else if (3'h0 == stateReg) begin // @[VendingMachine.scala 43:21]
      if (buy) begin // @[VendingMachine.scala 51:17]
        if (enough) begin // @[VendingMachine.scala 52:23]
          stateReg <= 3'h3; // @[VendingMachine.scala 53:20]
        end else begin
          stateReg <= 3'h5; // @[VendingMachine.scala 55:20]
        end
      end else if (coin5) begin // @[VendingMachine.scala 48:19]
        stateReg <= 3'h2; // @[VendingMachine.scala 49:18]
      end else begin
        stateReg <= _GEN_0;
      end
    end else if (3'h1 == stateReg) begin // @[VendingMachine.scala 43:21]
      stateReg <= 3'h0; // @[VendingMachine.scala 60:16]
    end else if (3'h2 == stateReg) begin // @[VendingMachine.scala 43:21]
      stateReg <= 3'h0; // @[VendingMachine.scala 63:16]
    end else begin
      stateReg <= _GEN_9;
    end
  end
// Register and memory initialization
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
`ifdef FIRRTL_BEFORE_INITIAL
`FIRRTL_BEFORE_INITIAL
`endif
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
`ifdef RANDOMIZE_REG_INIT
  _RAND_0 = {1{`RANDOM}};
  coin2_REG = _RAND_0[0:0];
  _RAND_1 = {1{`RANDOM}};
  coin2_REG_1 = _RAND_1[0:0];
  _RAND_2 = {1{`RANDOM}};
  coin2_REG_2 = _RAND_2[0:0];
  _RAND_3 = {1{`RANDOM}};
  coin5_REG = _RAND_3[0:0];
  _RAND_4 = {1{`RANDOM}};
  coin5_REG_1 = _RAND_4[0:0];
  _RAND_5 = {1{`RANDOM}};
  coin5_REG_2 = _RAND_5[0:0];
  _RAND_6 = {1{`RANDOM}};
  buySync_REG = _RAND_6[0:0];
  _RAND_7 = {1{`RANDOM}};
  buySync = _RAND_7[0:0];
  _RAND_8 = {1{`RANDOM}};
  buy_REG = _RAND_8[0:0];
  _RAND_9 = {1{`RANDOM}};
  regSum = _RAND_9[7:0];
  _RAND_10 = {1{`RANDOM}};
  stateReg = _RAND_10[2:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
endmodule
