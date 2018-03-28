
X = 115;
x = [100, 121, 144, 169];

for i = 1:length(x)
   for j = 1:length(x)
       if abs(x(i) - X) < abs(x(j) - X)
           aux = x(i);
           x(i) = x(j);
           x(j) = aux;
       end
   end
end

disp(x);

fx = sqrt(x);

e = 0.001;
f = zeros(length(x), length(x));
f(:, 1) = fx';

for i = 2 : length(x)
    for j = 1 : i
        f(i, j + 1) = 1 / (x(i) - x(j)) * (f(j, j) * (x(i) - X) - f(i, j) * (x(j) - X));
    end
end

v = diag(f);

ok = 0;
for i = 3 : length(v)
    if abs(v(i) - v(i - 1)) <= e
        ok = 1;
        fprintf("aproximation of sqrt(%d) is: %f\n", X, v(i));
    end
end

if ok == 0
    fprintf("There are not enough nodes!\n");
end
