%====================================================================================
% cleaner24 description   
%====================================================================================
dispatch( stepdone, stepdone(V) ). %automessaggio
dispatch( stepfail, stepfail(X) ). %automessaggio
event( alarm, alarm(X) ).
dispatch( pause, pause(X) ).
%====================================================================================
context(ctxcleaner24, "localhost",  "TCP", "8032").
 qactor( cleaner24, ctxcleaner24, "it.unibo.cleaner24.Cleaner24").
 static(cleaner24).
  qactor( sonarmock, ctxcleaner24, "it.unibo.sonarmock.Sonarmock").
 static(sonarmock).
  qactor( sentinel, ctxcleaner24, "it.unibo.sentinel.Sentinel").
 static(sentinel).
