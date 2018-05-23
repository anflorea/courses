f = @(x) x^3 - x^2 - 1;

x0 = 1;
x1 = 2;

epsilon = 10^-4;

N = 100;

xk2 = x0;
xk1 = x1;
k = 0;
while k < N
   k = k + 1;
   xk = xk1 - ((xk1 - xk2) * f(xk1)) / (f(xk1) - f(xk2));
   if abs(xk - xk1) < epsilon
       break;
   end
   xk2 = xk1;
   xk1 = xk;
end

fprintf("The result is %f after %d iterations.\n", xk, k);