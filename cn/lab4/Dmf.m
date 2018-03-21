function d = Dmf(x, f)

    d = zeros(length(x), length(x));

    d(:,1) = f';

    for j = 2:length(x)
       for i = 1:length(x) - j + 1
            d(i, j) = (d(i + 1, j - 1) - d(i, j - 1)) / (x(i + j - 1) - x(i));
       end
    end
end