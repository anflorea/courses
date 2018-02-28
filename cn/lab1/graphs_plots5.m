function y = graphs_plots5(x)
    if (x > 0)
        y = 1 + (1 / graphs_plots5(x - 1));
    else
        y = 1;
    end
end