function [x, k] = Relaxation(N, A, b, x0, epsilon, omega)

    [n, ~] = size(A);

    x = x0;

    k = 0;
    while k < N
        k = k + 1;
        x0 = x;
        for i = 1 : n
            xi = x;
            xi(i) = 0;
            x(i) = (b(i) - A(i, 1 : n) * xi) / A(i, i) * omega + (1 - omega) * x0(i);
        end
        
        if (norm(x - x0) < epsilon)
            break;
        end
    end

end