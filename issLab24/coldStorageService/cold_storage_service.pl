%====================================================================================
% cold_storage_service description   
%====================================================================================
request( store, store(N) ).
reply( store_accepted, store_accepted(TICKET) ). %%for store | ticket è un numero intero
reply( store_rejected, store_rejected(X) ).  %%for store
%====================================================================================
context(ctx_css, "localhost",  "TCP", "8080").
 qactor( system, ctx_css, "it.unibo.system.System").
 static(system).
  qactor( driver_mock, ctx_css, "it.unibo.driver_mock.Driver_mock").
 static(driver_mock).