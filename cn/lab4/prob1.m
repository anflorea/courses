x = [1, 2, 3, 4, 5];
y = [22, 23, 25, 30, 28];

fprintf("Newton interpolation at 2.5 is: %f\n", Newton(x, y, 2.5));
X = linspace(0, 5, 100);
% Y = Newton(x, y, X);
Y = zeros(length(X));
for i = 1 : length(X)
   Y(i) = Newton(x, y, X(i));
end

plot(x, y, "o")
hold on;
plot(X, Y)