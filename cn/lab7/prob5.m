a = 0;
b = pi;
f = @(x) 1 ./ (4 + sin(20 .* x));

n = 10;
h = (b - a) / n;
xk = a + (0 : n) * h;

repeatedSimpson = ((b - a) / (6 * n)) * (f(a) + f(b) + 4 * sum(f((xk(1 : n) + xk(2 : n + 1)) / 2)) + 2 * sum(f(xk(2 : n))));
fprintf('Repeated Simpson result with n = 10: %2.7f\n', repeatedSimpson);

n = 30;
h = (b - a) / n;
xk = a + (0 : n) * h;
repeatedSimpson = ((b - a) / (6 * n)) * (f(a) + f(b) + 4 * sum(f((xk(1 : n) + xk(2 : n + 1)) / 2)) + 2 * sum(f(xk(2 : n))));
fprintf('Repeated Simpson result with n = 30: %2.7f\n', repeatedSimpson);