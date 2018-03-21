x1 = linspace(0, 6, 13);
x2 = linspace(0, 6, 100);

f = @(x) exp(sin(x));
y1 = f(x1);
y2 = f(x2);
Y = zeros(length(x2));
for i = 1 : length(x2)
    Y(i) = Newton(x1, y1, x2(i));
end

plot(x2, y2, x1, y1, 'o', x2, Y, '--');
legend('Function', 'Interpolation points', 'Newton polynomial');
