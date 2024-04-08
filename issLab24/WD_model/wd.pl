%====================================================================================
% wd description   
%====================================================================================
dispatch( turnOn, turnOn(N) ).
dispatch( turnOff, turnOff(N) ).
dispatch( foto, foto(X) ).
event( detection, detection(X) ).
event( detectionEND, detectionEND(X) ).
%====================================================================================
context(ctx_bosco, "127.0.0.1",  "TCP", "8080").
context(ctx_stazione, "localhost",  "TCP", "8000").
 qactor( sonar, ctx_bosco, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( led_blu, ctx_bosco, "it.unibo.led_blu.Led_blu").
 static(led_blu).
  qactor( fotocamera, ctx_bosco, "it.unibo.fotocamera.Fotocamera").
 static(fotocamera).
  qactor( stazione, ctx_stazione, "it.unibo.stazione.Stazione").
 static(stazione).
  qactor( elaboratore, ctx_stazione, "it.unibo.elaboratore.Elaboratore").
 static(elaboratore).
  qactor( led_rosso, ctx_stazione, "it.unibo.led_rosso.Led_rosso").
 static(led_rosso).
