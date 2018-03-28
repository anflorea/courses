function Hmf = Hermite(x, f, df, X)
    z = zeros(1, length(x) * 2);
    newF = zeros(1, length(z));
    i = 1;
    for k = 1 : length(x)
        z(i) = x(k);
        z(i + 1) = x(k);
        newF(i) = f(k);
        newF(i + 1) = f(k);
        i = i + 2;
    end
    
    d(:,1) = newF';
    
    for i = 1:length(z) - 1
       if mod(i, 2) == 1
           d(i, 2) = df(fix(i / 2) + 1);
       else
           d(i, 2) = (d(i + 1, 1) - d(i, 1)) / (z(i + 1) - z(i));
       end
    end
    
    for j = 3:length(z)
       for i = 1:length(z) - j + 1
            d(i, j) = (d(i + 1, j - 1) - d(i, j - 1)) / (z(i + j - 1) - z(i));
       end
    end
    
    A = d(1, 2 : end);
    C = cumprod(X - z(1:end - 1));
    Hmf = (A * C') + f(1);
end