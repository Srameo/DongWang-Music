function sim = calculateSim(x)
[row, ~] = size(x);
sim = zeros(row);

for i = 1:row
    for j = i + 1:row
        sim(i, j) = personSim(x(i, :), x(j, :));
    end
    fprintf("user %d calculate sim finished!\n", i);
end

sim = sim + sim' + eye(size(sim));

end