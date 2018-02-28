x = -2 : 0.1 : 2;
y = -4 : 0.1 : 4;

[x, y] = meshgrid(x, y);

g = @(x, y) exp(-((x - 1 / 2).^2 + (y - 1 / 2).^2));


mesh(x, y, g(x, y))