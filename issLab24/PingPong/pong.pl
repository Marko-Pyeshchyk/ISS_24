%====================================================================================
% pong description   
%====================================================================================
request( hit, hit(N) ).
dispatch( miss, miss(N) ).
reply( hit_resp, hit(N) ).  %%for hit
reply( miss_resp, miss(N) ).  %%for hit
%====================================================================================
context(ctx_pong, "localhost",  "TCP", "8001").
 qactor( pong, ctx_pong, "it.unibo.pong.Pong").
 static(pong).
