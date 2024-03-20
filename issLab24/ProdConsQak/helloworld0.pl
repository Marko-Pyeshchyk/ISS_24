%====================================================================================
% helloworld0 description   
%====================================================================================
dispatch( info, info(N,S) ).
dispatch( ack, ack(N) ).
%====================================================================================
context(ctx0, "localhost",  "TCP", "8000").
 qactor( consumer, ctx0, "it.unibo.consumer.Consumer").
 static(consumer).
  qactor( producer1, ctx0, "it.unibo.producer1.Producer1").
 static(producer1).
  qactor( producer2, ctx0, "it.unibo.producer2.Producer2").
 static(producer2).
