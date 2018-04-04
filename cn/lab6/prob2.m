x = [0 10 20 30 40 60 80 100];
y = [0.0061 0.0123 0.0234 0.0424 0.0738 0.1992 0.4736 1.0133];
p45 =  0.095848;
d1 = 1;
d2 = 2;

p1 = polyfit(x, y, d1);
p2 = polyfit(x, y, d2);

li = linspace(0, 100, 100);

sa1 = polyval(p1, li);
sa2 = polyval(p2, li);

lag = zeros(1, length(li));
for i = 1 : length(li)
    lag(i) = Lagrange(x, y, li(i));
end

aprox1 = polyval(p1, 45);
aprox2 = polyval(p2, 45);

format = "Value for T = 45, using polynom of degree %d, is: %f, and the error is: %f\n";

fprintf(format, d1, aprox1, abs(aprox1 - p45));
fprintf(format, d2, aprox2, abs(aprox2 - p45));

plot(x, y, 'o', li, sa1, li, sa2, li, lag);
legend('Interpolation points', ['Least suqare aprox of deg. ' num2str(d1)], ['Least square aprox of deg. ' num2str(d2)], 'Lagrange polynomial');


