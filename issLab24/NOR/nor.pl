%====================================================================================
% nor description   
%====================================================================================
dispatch( in, in(X,Y) ).
dispatch( out, out(X) ).
%====================================================================================
context(ctx1, "localhost",  "TCP", "8080").
 qactor( nor, ctx1, "it.unibo.nor.Nor").
 static(nor).
  qactor( mock_user, ctx1, "it.unibo.mock_user.Mock_user").
 static(mock_user).
