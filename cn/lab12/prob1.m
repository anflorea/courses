f = @(x) cos(x) - x;
fd = @(x) -sin(x) - 1;

x0 = pi / 4;
epsilon = 10^-4;
N = 100;

xk = x0;
k = 0;
while k < N
    k = k + 1;
    xk1 = xk - f(xk) / fd(xk);
    if abs(xk1 - xk) < epsilon
        break;
    end
    xk = xk1;
end

fprintf("The result is: %f after %d iterations.\n", xk, k);