r = 110;
p = 75;
a = 0;
b = 2 * pi;

f = @(x) (1 - ((p / r)^2) .* sin(x)) .^ (1 / 2);

n = 10;
h = (b - a) / n;
xk = a + (0 : n) * h;

repeatedTrapez = ((b - a) / (2 * n)) * (f(a) + f(b) + 2 * sum(f(xk(2 : n))));
aproximation = ((60 * r) / (r^2 - p^2)) * repeatedTrapez;

fprintf("Repeated Trapezium Formula result: %f\n", aproximation);