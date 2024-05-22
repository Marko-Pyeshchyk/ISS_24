%====================================================================================
% ffd description   
%====================================================================================
dispatch( in, in(X) ).
dispatch( out, out(X) ).
%====================================================================================
context(ctx_ffd, "localhost",  "TCP", "8080").
 qactor( nor_s, ctx_ffd, "it.unibo.nor_s.Nor_s").
 static(nor_s).
  qactor( nor_r, ctx_ffd, "it.unibo.nor_r.Nor_r").
 static(nor_r).
  qactor( mock_user, ctx_ffd, "it.unibo.mock_user.Mock_user").
 static(mock_user).
