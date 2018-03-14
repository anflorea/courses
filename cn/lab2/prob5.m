x = 2 : 2 : 8;

f = 4 : 4 : 16;
f(3) = 14;

d = zeros(4, 4);

d(:,1) = f';

for j = 2:4
   for i = 1:4 - j + 1
        d(i, j) = (d(i + 1, j - 1) - d(i, j - 1)) / (x(i + 1) - x(i));
   end
end

disp(d);