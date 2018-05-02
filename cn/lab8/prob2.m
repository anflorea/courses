a = 0;
b = 1;

epsilon = 10 ^ -5;

f = @(x) 2 ./ (1 + x.^2);

QT0 = (b - a) / 2 * (f(a) + f(b));
QT1 = (b - a) / 4 * (f(a)  + 2 * f(a + ((b - a) / 2)) + f(b));

h = b - a;

k = 1;
while abs(QT1 - QT0) > epsilon
    k = k + 1;
    QT0 = QT1;
    theSum = 1 : 2^(k - 1);
    theSum = f(a + ((2 * theSum - 1) / (2^k)) * h);
    QT1 = 1 / 2 * QT0 + h / (2^k) * sum(theSum);
end

fprintf("The result is: %.10f; k = %d\n", QT1, k);