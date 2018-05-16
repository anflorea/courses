correctResult = 0.6362943688583;
epsilon = 10^-3;

a = 1;
b = 2;

f = @(x) x .* log(x);

n = 0;
repeatedTrapez = 0;
while abs(round(correctResult / epsilon) * epsilon - repeatedTrapez) >= epsilon
    n = n + 1;
    h = (b - a) / n;
    xk = a + (0 : n) * h;

    repeatedTrapez = ((b - a) / (2 * n)) * (f(a) + f(b) + 2 * sum(f(xk(2 : n))));
end
fprintf('Repeated trapez result: %2.7f for n = %d\n', repeatedTrapez, n);