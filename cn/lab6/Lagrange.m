function Lmfx = Lagrange(x, y, val)
    p1 = 0;
    p2 = 0;

    for i = 1 : length(x)
        p1 = p1 + (Ai(x, i) * y(i) / (val - x(i)));
    end

    for i = 1 : length(x)
        p2 = p2 + (Ai(x, i) / (val - x(i)));
    end

    Lmfx = p1 / p2;
end