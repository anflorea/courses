a = 1;
b = 3;

epsilon = 10 ^ -4;

f = @(x) 100 ./ x.^2 .* sin(10 ./ x);

X = linspace(a, b, 1000);

plot(X, f(X));

fprintf("Adaptive formula: %.10f\n", adquad(a, b, epsilon));

n1 = 50;
n2 = 100;

res1 = repetedRectangle(f, a, b, n1);
res2 = repetedRectangle(f, a, b, n2);

fprintf("Repeted rectangle formula for n = %d is: %.10f\n", n1, res1);
fprintf("Repeted rectangle formula for n = %d is: %.10f\n", n2, res2);

function I = Simpson(a, b)
    f = @(x) 100 ./ x.^2 .* sin(10 ./ x);
    
    I = (b - a) / 2 * (f(a) + f(b));
end

function I = adquad(a, b, er)
    I1 = Simpson(a, b);
    I2 = Simpson(a, (a + b) / 2) + Simpson((a + b) / 2, b);
    
    if abs(I1 - I2) < 15 * er
        I = I2;
        return;
    else
        I = adquad(a, (a + b) / 2, er / 2) + adquad((a + b) / 2, b, er / 2);
    end
end