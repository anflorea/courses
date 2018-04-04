function res = Ai(xnodes, i)
    p = 1;
    for x = 1 : length(xnodes)
        if (x ~= i)
            p = p * (xnodes(i) - xnodes(x));
        end
    end
    res = 1 / p;
end