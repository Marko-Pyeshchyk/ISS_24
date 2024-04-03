%====================================================================================
% ref description   
%====================================================================================
dispatch( ball, ball(N) ).
dispatch( ballview, ball(N) ).
request( info, info(X) ).
reply( obsinfo, obsinfo(X) ).  %%for info
%====================================================================================
context(ctxtest, "localhost",  "TCP", "8080").
 qactor( ping, ctxtest, "it.unibo.ping.Ping").
 static(ping).
  qactor( pong, ctxtest, "it.unibo.pong.Pong").
 static(pong).
  qactor( referee, ctxtest, "it.unibo.referee.Referee").
 static(referee).
