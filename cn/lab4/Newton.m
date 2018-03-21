function Nmfx = Newton(x, f, X)
    A = Dmf(x, f);
    A = A(1, 2 : end);
    C = cumprod(X - x(1:end-1));
    Nmfx = (A * C') + f(1);
end