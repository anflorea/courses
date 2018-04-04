x = -3 : 0.4 : 3;
y = sin(x);

p = polyfit(x, y, 4);

xx = linspace(-3, 3, 1000);

lsp = polyval(p, xx);

plot(x, y, 'o', xx, lsp);
legend('Interpolation points', 'Least squares polynomial');