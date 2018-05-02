A = [10 7 8 7; 7 5 6 5; 8 6 10 9; 7 5 9 10];
b = [32; 23; 33; 31];

res = A \ b;

fprintf("The conditioning number of the system is: %f and the result is: \n", cond(A));
disp(res);

b2 = [32.1; 22.9; 33.1; 30.9];
res2 = A \ b2;

fprintf("The result for b) is: \n");
disp(res2);

ire = norm(b - b2) / norm(b2);
ore = norm(res - res2) / norm(res2);

fprintf("Input relativ error: %f\nOutput relative error: %f\n", ire, ore);
fprintf("ORE / IRE = %f\n", ore / ire);

A2 = [10 7 8.1 7.2; 7.08 5.04 6 5; 8 5.98 9.89 9; 6.99 4.99 9 9.98];

res3 = A2 \ b;

fprintf("\n");
disp(res3);

ire = norm(A - A2) / norm(A2);
ore = norm(res - res3) / norm(res3);
fprintf("Input relativ error: %f\nOutput relative error: %f\n", ire, ore);
fprintf("ORE / IRE = %f\n", ore / ire);