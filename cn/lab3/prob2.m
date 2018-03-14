x = [100, 121, 144];
y = [10, 11, 12];

val = input('x = ');

p1 = 0;
p2 = 0;

for i = 1 : length(x)
    p1 = p1 + (Ai(x, i) * y(i) / (val - x(i)));
end

for i = 1 : length(x)
    p2 = p2 + (Ai(x, i) / (val - x(i)));
end

Lmfx = p1 / p2;

fprintf("%f\n", Lmfx);