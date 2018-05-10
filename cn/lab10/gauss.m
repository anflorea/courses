function x = gauss(A, b)
    [n, ~] = size(A);
    E = [A b];
    
    for p = 1 : n - 1
        [~, q] = max(abs(E(p:n, p)));
        q = q + p - 1;
        E([p q], :) = E([q p], :);
        for i = p + 1 : n
            E(i, p:n+1) = E(i, p:n+1) - E(i, p) / E(p, p) * E(p, p:n+1);
        end
    end
    
    disp(E);
    
    if (E(n, n) == 0)
        printf("Message");
        exit();
    end
    
    x = zeros(n, 1);
    A = E(1:n, 1:n);
    b = E(:, n+1);
    x(n) = b(n) / A(n, n);
    
    for i = n-1 : -1 : 1
        x(i) = (b(i) - A(i, :) * x) / A(i, i);
    end
end