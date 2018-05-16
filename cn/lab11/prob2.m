A = [3 1 1; -2 4 0; -1 2 -6];

b = [12; 2; -5];

[n, ~] = size(A);

D = diag(diag(A));
L = tril(A) - D;
U = triu(A) - D;

epsilon = 10^-3;

N = 50;

% Jacobi matriceal method

x0 = zeros(n, 1);
x = x0;

k = 0;
while k < N
    k = k + 1;
    x = inv(D) * (-(L + U) * x0 + b);
    
    if (norm(x - x0) < epsilon)
        break;
    end
        
    x0 = x;
end

fprintf("Steps using Jacobi matriceal method: %d\n", k);
disp(x);

% Gauss-Seidel matriceal method

x0 = zeros(n, 1);
x = x0;

k = 0;
while k < N
    k = k + 1;
    x = inv(D + L) * (-U * x0 + b);
    
    if (norm(x - x0) < epsilon)
        break;
    end
        
    x0 = x;
end

fprintf("Steps using Gauss-Seidel matriceal method: %d\n", k);
disp(x);

% relaxation matriceal method

omega = 1.01;

x0 = zeros(n, 1);
x = x0;

k = 0;
while k < N
    k = k + 1;
    x = inv(D + omega * L) * (((1 - omega) * D - omega * U) * x0 + omega * b);
    
    if (norm(x - x0) < epsilon)
        break;
    end
        
    x0 = x;
end

fprintf("Steps using relaxation matriceal method: %d\n", k);
disp(x);