function I = repetedRectangle(f, a, b, n)
    x1 = a + (b - a) / (2 * n);

    x = 1 : n;
    x = x1 + (x - 1) * ((b - a) / n);
    x(1) = x1;

    I = ((b - a) / n) * sum(f(x));
end