%====================================================================================
% test description   
%====================================================================================
dispatch( pause, pause(X) ).
dispatch( data, data(X) ).
event( distance, distance(X) ).
%====================================================================================
context(ctxass, "localhost",  "TCP", "8047").
 qactor( sonardevice, ctxass, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
