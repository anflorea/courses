x = -1 : 0.01 : 3;

x0 = 0;
f = @(x) exp(x);

num = input('n = ');

plot(x, exp(x), 'c', 'LineWidth', 3);
hold on;

for n = 1:num
    sum = 1;
    aux = 1;
    for k = 1:n
        aux = aux .* (x - x0) / k;
        sum = sum + (aux .* f(x0));
    end
    plot(x, sum);
    hold on;
end
