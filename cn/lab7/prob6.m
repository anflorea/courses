a = 0;
b = 0.5;
f = @(x) exp(1) .^ (-x.^2);


n = 4;
h = (b - a) / n;
xk = a + (0 : n) * h;
repeatedSimpson = ((b - a) / (6 * n)) * (f(a) + f(b) + 4 * sum(f((xk(1 : n) + xk(2 : n + 1)) / 2)) + 2 * sum(f(xk(2 : n))));
erf = 2 / sqrt(pi) * repeatedSimpson;
fprintf('Repeated Simpson erf result with n = 4 : %2.7f\n', erf);


n = 10;
h = (b - a) / n;
xk = a + (0 : n) * h;
repeatedSimpson = ((b - a) / (6 * n)) * (f(a) + f(b) + 4 * sum(f((xk(1 : n) + xk(2 : n + 1)) / 2)) + 2 * sum(f(xk(2 : n))));
erf = 2 / sqrt(pi) * repeatedSimpson;
fprintf('Repeated Simpson erf result with n = 10: %2.7f\n',erf);