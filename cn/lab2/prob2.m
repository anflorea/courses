x = -1 : 0.01 : 1;

n = input('n = ');

for i = 0:n
    plot(x, cheby(i, x))
    hold on;
end

function res = cheby(n, x)
    if (n == 0 || n == 1)
        if (n == 0)
            res = 1;
        else
            res = x;
        end
    else
        res = 2 * x .* cheby(n - 1, x) - cheby(n - 2, x);
    end
end