for n = 10 : 15
   Vn = zeros(n, n);
   
   for i = 1 : n
       vi = 1 / i;
       t = 1;
       for j = 1 : n
           Vn(i, j) = t;
           t = t * vi;
       end
   end
   fprintf("Condition number for V%d is: %f\n", n, cond(Vn));
end

for n = 10 : 15
    Vn = zeros(n, n);
    
    for i = 1 : n
       vi = -1 + 2 / n * i;
       t = 1;
       for j = 1 : n
          Vn(i, j) = t;
          t = t * vi;
       end
    end
    fprintf("Condition number for V%d is: %f\n", n, cond(Vn));
end