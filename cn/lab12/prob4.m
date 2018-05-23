f = @(x) cos(x) - x;

a = 0.5;
b = pi / 4;

epsilon = 10^-4;
N = 100;

k = 0;
while k < N
    k = k + 1;
    c = (a * f(b) - b * f(a)) / (f(b) - f(a));
    if f(a) * f(b) < 0
        b = c;
    else
        a = c;
    end
    if abs(a - b) < epsilon
        break;
    end
end

fprintf("The result is %f after %d iterations.\n", (a + b) / 2, k);