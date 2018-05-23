a = 1;
b = 2;
f = @(x) (x - 2)^2 - log(x);

epsilon = 10^-4;

N = 100;

k = 0;
while k < N
    k = k + 1;
    c = (a + b) / 2;
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