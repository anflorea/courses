function [x, k] = Jacobi(N, A, b, x0, epsilon)

    [n, ~] = size(A);

    x = x0;

    k = 0;
    while k < N
        k = k + 1;
        for i = 1 : n
            x0i = x0;
            x0i(i) = 0;
            x(i) = (b(i) - A(i, 1 : n) * x0i) / A(i, i);
        end
        
        if (norm(x - x0) < epsilon)
            break;
        end
            
        x0 = x;
    end

end