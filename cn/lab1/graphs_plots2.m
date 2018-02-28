t = 0 : 0.01 : 10 * pi;

a = input('a=');
b = input('b=');

x = @(t) (a + b) * cos(t) - b * cos((a / b + 1) * t);
y = @(t) (a + b) * sin(t) - b * sin((a / b + 1) * t);

plot(x(t), y(t))