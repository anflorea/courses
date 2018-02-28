x = 0 : 0.01 : 2 * pi;

f1 = @(x) cos(x);
f2 = @(x) sin(x);
f3 = @(x) cos(2 * x);

y1 = cos(x);
y2 = sin(x);
y3 = cos(2 * x);

%plot(x, y1, 'r', x, y2, 'b', x, y3, 'y')
plot(x, f1(x), 'r', x, f2(x), 'b', x, f3(x), 'y')