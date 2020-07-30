function p = predict(x, sim, u, k)

[st, index] = sort(sim, 2, 'descend');
simk = st(u, 1:k); % 前 k 个临近用户的相似度
idxk = index(u, 1:k);   % 前 k 个临近用户的id
m = mean(x, 2);     % 所有用户评分的平均值
mu = m(u, :);       % 目标用户评分的平均值

if sum(simk) ~= 0
    p = mu + simk * (x(idxk, :) - m(idxk)) / sum(simk);
else
    p = zeros(1, size(x, 2));
end
fprintf("predict %d finished!\n", u);
end