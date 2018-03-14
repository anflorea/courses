step = 10 / 20;

x1 = 0 : step : 10;
x2 = 0 : 0.01 : 10;

f = @(x) (1 + cos(pi * x)) ./ (1 + x);

y1 = zeros(1, length(x2));

k = 0;
for i = 0 : 0.01 : 10
    k = k + 1;
    y1(k) = Lagrange(x1, f(x1), i);
end

plot(x2, f(x2), 'r', x1, f(x1), 'o', x2, y1, '--b');
legend('Function', '21 equidistant points', 'Lagrange polynomial');