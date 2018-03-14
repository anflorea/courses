x = 1930 : 10 : 1980;
y = [123203, 131669, 150697, 179323, 203212, 226505];

p1 = 0;
p2 = 0;

val = input('x = ');

for i = 1 : length(x)
    p1 = p1 + (Ai(x, i) * y(i) / (val - x(i)));
end

for i = 1 : length(x)
    p2 = p2 + (Ai(x, i) / (val - x(i)));
end

Lmfx = p1 / p2;

fprintf("%f\n", Lmfx);