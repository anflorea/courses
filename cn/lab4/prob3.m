x = 1 : 2 : 115;
x1 = sqrt(x);

m = length(x);

X = 115;
n=length(x);
D = x1 - x;
A(:,1) = f;

for i = 2 : n
    for j = 2 : i
        A(i, j) = (D(j - 1) * A(i, j - 1) - D(i) * A(j - 1, j -1 )) / (x(i) - x(j - 1));
    end
end


disp(A(n,n));
