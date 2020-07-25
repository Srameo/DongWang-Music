%% ===== init ===== %%
clear all; clc;

%% ===== load Data ===== %%

A = load("user_artists.dat");
a=sparse(A(:,1)', A(:,2)', A(:,3)');%第一个是行，第二个是列，第三个是具体的数
b = full(a);

%% ===== compute Rating ===== %%
[row, column] = size(b);
for i = 1:row
    temp = b(i, b(i,:) > 0);
    m = mean(temp);
    s = std(temp);
    b(i, b(i,:) > 0) = sigmoid((temp - m) / s);
end

%% ===== compute person ===== %%
% b = b * b';
for i = 1:row
    mu = mean(b(i, b(i, :) > 0));
    if isempty(mu)
        mu = 0;
    end
    b(i, :) = b(i, :) + mu * ones(1, column);
end
b(isnan(b)) = 0;
% c = b * b';
% c(isnan(c)) = 0;
% [U, ~, ~] = svd(c);
c = corr(b');
c(isnan(c)) = -1;
[st, index] = sort(c, 2, 'descend');

%% ===== ===== %%
idx = 2;
tgt = 52;
K = 50;
mu = mean(b(idx, :));
count = 0;
for i = 2:1 + K
    j = index(idx, i);
    mv = mean(b(j, :));
    count = count + (b(j, tgt) - mv) * st(idx, i);
end
ans = mu + count / sum(abs(st(idx, 2: 1 + K)));

