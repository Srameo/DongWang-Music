function X = addData(x, sim)
[row, ~] = size(x);
X = sim * x;
for i = 1:row
    sumsim = sum(sim(i, :));
    if sumsim == 0
        continue
    end
    X(i, :) = X(i, :) / sumsim;
    fprintf("user %d add data finished!\n", i);
end
end