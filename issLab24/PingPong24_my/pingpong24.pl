%====================================================================================
% pingpong24 description   
%====================================================================================
dispatch( ball, ball(N) ). %info exchanged
%====================================================================================
context(ctxtest, "localhost",  "TCP", "8014").
 qactor( ping, ctxtest, "it.unibo.ping.Ping").
 static(ping).
  qactor( pong, ctxtest, "it.unibo.pong.Pong").
 static(pong).
