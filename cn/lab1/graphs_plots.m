x = 0 : 0.01 : 1;
y = exp(10 * x .* (x - 1)) .* sin (12 * pi * x);
% plot(x, y)

f = @(x) exp(10 * x .* (x - 1)) .* sin (12 * pi * x);

%plot(f(x), 'b')
%title('Awsm plot')

f2 = @(x) exp(5 * x.^2 - 1) .* sqrt(3 * cos(12 * pi * x));

plot(f2(x), 'r')
title('Another awsm plot')