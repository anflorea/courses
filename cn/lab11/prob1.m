% A = [3 -1 0 0 0 0; -1 3 -1 0 0 0; 0 -1 3 -1 0 0; 0 0 -1 3 -1 0; 0 0 0 -1 3 -1; 0 0 0 0 -1 3];
A = diag(ones(6, 1) * 3, 0) + diag(ones(5, 1) * -1, 1) + diag(ones(5, 1) * -1, -1);

b = [2; 1; 1; 1; 1; 2];

[x, k] = Jacobi(20, A, b, zeros(6, 1), 0.001);

fprintf("Using Jacobi method, after %d iterations, the solution is:\n", k);
disp(x);

[x, k] = GaussSeidel(20, A, b, zeros(6, 1), 0.001);

fprintf("Using Gauss-Seidel method, after %d iterations, the solution is:\n", k);
disp(x);

[x, k] = Relaxation(50, A, b, zeros(6, 1), 0.001, 1.1);

fprintf("Using relaxation method, after %d iterations, the solution is:\n", k);
disp(x);