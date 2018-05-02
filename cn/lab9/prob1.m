A = [400 -201; -800 401];
b = [200; -200];

res = A \ b;

disp(res);

A2 = A;
A2(1, 1) = 401;

res2 = A2 \ b;

disp(res2);


fprintf("%f\n", cond(A));