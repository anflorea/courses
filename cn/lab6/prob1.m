x = linspace(1, 7, 7);
y = [13, 15, 20, 14, 15, 13, 10];

m = length(x) - 1;

a = ((m + 1) * x * y' - sum(x) * sum(y)) / ((m + 1) * sum(x.^2) - sum(x)^2);

b = (sum(x.^2) * sum(y) - x * y' * sum(x)) / ((m + 1) * sum(x.^2) - sum(x)^2);

xx = linspace(1, 7, 1000);
yy = a * xx + b;

E = sum((y - (a * x + b)).^2)^(1/2);

fprintf("The temperature at 8:00 is: %f\n", a * 8 + b);
fprintf("The minimum value is: %f\n", E);

plot(x, y, 'o', xx, yy);