%====================================================================================
% sonar description   
%====================================================================================
event( sonardata, distance(X) ).
%====================================================================================
context(ctxsonarqak24, "localhost",  "TCP", "8080").
 qactor( sonarusagemock, ctxsonarqak24, "it.unibo.sonarusagemock.Sonarusagemock").
 static(sonarusagemock).
  qactor( sonar24, ctxsonarqak24, "it.unibo.sonar24.Sonar24").
 static(sonar24).
  qactor( sonardevice, ctxsonarqak24, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
