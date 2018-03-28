x = [1, 2];
f = [0, 0.6931];
df = [1, 0.5];

res = Hermite(x, f, df, 1.5);
fprintf("f(1.5) = %f\n", res);