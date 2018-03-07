h = 0.25;
a = 1;

f = @(x) sqrt(5 * x.^2 + 1);

m = zeros(6, 6);

for i = 0:6
    ai = a + i * h;
    m(i + 1, 1) = f(ai);
end

% a = 1 : 0.25 : 1 + 6 * 0.25

for j = 2:7
   for i = 1:8-j
        m(i, j) = m(i + 1, j - 1) - m(i, j - 1);
   end
end

disp(m);