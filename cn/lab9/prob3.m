for n = 10 : 15
    Hn = zeros(n, n);
    for i = 1 : n
        for j = 1 : n
            Hn(i, j) = 1 / (i + j - 1);
        end
    end
    fprintf("Condition number for H%d is: %f\n", n, cond(Hn)); 
end