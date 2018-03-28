x1 = linspace(-5, 5, 1000);
x2 = linspace(-5, 5, 15);

y1 = sin(2 * x1);
y2 = sin(2 * x2);

dy2 = 2 * cos(2 * x2);

Y = zeros(length(x1), 1);
for i = 1 : length(x1)
    Y(i) = Hermite(x2, y2, dy2, x1(i));
end

plot(x1, y1, x2, y2, 'o', x1, Y, 'g--');
legend('Function', 'Interpolation points', 'Hermite polynomial');