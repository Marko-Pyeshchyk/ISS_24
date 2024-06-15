%====================================================================================
% wis24 description   
%====================================================================================
event( burn_end, burn_end(N) ).
%====================================================================================
context(ctxwis, "localhost",  "TCP", "8080").
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( wis, ctxwis, "it.unibo.wis.Wis").
 static(wis).
  qactor( incinerator, ctxwis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( op_robot, ctxwis, "it.unibo.op_robot.Op_robot").
 static(op_robot).
  qactor( waste_storage, ctxwis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
  qactor( monitoring_device, ctxwis, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctxwis, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctxwis, "it.unibo.sonar.Sonar").
 static(sonar).
