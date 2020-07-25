%% ===== init ===== %%
clear all; clc;

%% ===== load Data ===== %%

A = load("user_artists.dat");
a=sparse(A(:,1)', A(:,2)', A(:,3)');%第一个是行，第二个是列，第三个是具体的数
b = full(a);

%% ===== compute Rating ===== %%
[row, column] = size(b);
temp = b(b > 0);
m = mean(temp);
s = std(temp);
b(b > 0) = sigmoid((temp - m) / s);

%% ===== compute person ===== %%
sim = calculateSim(b);

%% ===== add1: 填充数据1 ===== %%
X = addData(b, sim);

%% ===== sim2: 第二次计算相似程度 ===== %%
sim2 = calculateSim(X);

%% ===== 计算预测评分 ===== %
k = 500;
u = 4;
p = predict(X, sim2, u, k);
[pst, pidx] = sort(p, 'descend');
[bst, bidx] = sort(b(u, :), 'descend');

%% ===== MAE ===== %
count = sum(b(u, :) > 0);
MAE = sum(abs(p(b(u, :) > 0) - b(u, b(u, :) > 0))) / count;

%% ===== test ===== %
all_p = zeros(row, column);
for i = 1:row
    all_p(i, :) = predict(b, sim, i, k);
end
all_p(isnan(all_p)) = 0;
count = sum(sum(b > 0));
MAE = sum(sum(abs(all_p(b > 0) - b(b > 0)))) / count;