a = 0;
b = 1;
f = @(x) 2 ./ (1 + x.^2);

trapezium = ((b - a) / 2) * (f(a) + f(b));

fprintf("Result with trapezium formula: %f\n", trapezium);

x = linspace(0, 1, 1000);
plot(x, f(x));
hold on;

q = [0, 0, 1, 1, 0];
w = [0, f(0), f(1), 0, 0];
plot(q, w, '*');

simpson = ((b - a) / 6) * (f(a) + f(b) + 4 * f((a + b) / 2));
fprintf("Result with Simpson formula: %f\n", simpson);