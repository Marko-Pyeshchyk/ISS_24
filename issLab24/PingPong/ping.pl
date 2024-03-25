%====================================================================================
% ping description   
%====================================================================================
request( hit, hit(A) ).
dispatch( miss, miss(N) ).
reply( hit_resp, hit(N) ).  %%for hit
reply( miss_resp, miss(N) ).  %%for hit
%====================================================================================
context(ctx_ping, "localhost",  "TCP", "8000").
context(ctx_pong, "127.0.0.1",  "TCP", "8001").
 qactor( pong, ctx_pong, "external").
  qactor( ping, ctx_ping, "it.unibo.ping.Ping").
 static(ping).
