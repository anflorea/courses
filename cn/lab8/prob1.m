
a = 1;
b = 1.5;

f = @(x) exp(-x.^2);

resA = (b - a) * f((a + b) / 2);

fprintf("a) The result using the rectangle formula is: %f\n", resA);

n1 = 150;

resB1 = repetedRectangle(f, a, b, n1);

fprintf("b) The result using the repeted rectangle formula for n = %d is: %.10f\n", n1, resB1);

n2 = 500;

resB2 = repetedRectangle(f, a, b, n2);

fprintf("b) The result using the repeted rectangle formula for n = %d is: %.10f\n", n2, resB2);
