axis([0, 3, 0, 5]);
[x, y] = ginput(10);

deg = 2;

xx = linspace(0, 3, 1000);

p = polyfit(x, y, deg);
yy = polyval(p, xx);

plot(x, y, 'o', xx, yy);
legend('Random interpolation points', ['Least squares polynomial of deg. ' num2str(deg)]);