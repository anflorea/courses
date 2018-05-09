A = [6 2 1 -1; 2 4 1 0; 1 1 4 -1; -1 0 -1 3];
b = [8; 7; 5; 1];

[~, n] = size(A);

l = A;
U = A;
L = eye(n);

for k = 1 : n - 1
    for i = k + 1 : n
        l(i, k) = U(i, k) / U(k, k);
    end
    t = zeros(n, 1);
    t(k+1:n) = l(k+1:n, k);
    e = zeros(1, n);
    e(k) = 1;
    M = eye(n) - t * e;
    U = M * U;
    L = L * inv(M);
end

disp(L);

z = zeros(n, 1);
z(1) = b(1) / L(1, 1);

for i = 2 : n
    z(i) = (b(i) - L(i, :) * z) / L(i, i);
end

disp(U);

x = zeros(n, 1);
x(n) = z(n) / U(n, n);

for i = n-1 : -1 : 1
    x(i) = (z(i) - U(i, :) * x) / U(i, i);
end

disp(x);