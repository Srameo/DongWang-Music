%% ===== init ===== %%
clear all; clc;

%% ===== load Data ===== %%

A = load("user_artists.dat");
a=sparse(A(:,1)', A(:,2)', A(:,3)');%第一个是行，第二个是列，第三个是具体的数
b = full(a);

%% ===== compute Rating ===== %%
[row, column] = size(b);
% 根据每个人对每个歌手的播放次数，将播放次数转换为评分
for i = 1:row
    temp = b(i, b(i,:) > 0);
    m = mean(temp);
    s = std(temp);
    b(i, b(i,:) > 0) = sigmoid((temp - m) / s);
end
b(isnan(b)) = 0;

%% ===== compute person ===== %%
% 计算第一次的相似度
sim = calculateSim(b);

%% ===== add1: 填充数据1 ===== %%
% 向稀疏矩阵中填充数据
X = addData(b, sim);

%% ===== sim2: 第二次计算相似程度 ===== %%
% 计算填充数据之后的相似度
sim2 = calculateSim(X);

%% ===== 计算预测评分 ===== %
k = 50;         % 挑选的临近用户数
all_p = zeros(row, column);
for i = 1:row
    all_p(i, :) = predict(b, sim, i, k);
end
all_p(isnan(all_p)) = 0;

%% ===== MAE ===== %
count = sum(sum(b > 0));         % 用户总共评分的总数
MAE = sum(sum(abs(all_p(b > 0) - b(b > 0)))) / count;

%% ===== precision ===== %
recommand_num = 30;     % 推荐个数
target_rate = 0.5;      % 分数阈值
[bst, bidx] = sort(b, 2, 'descend');
[pst, pidx] = sort(all_p, 2, 'descend');
precision = zeros(row, 1);
for i = 1:row
    pred = pidx(i, 1:recommand_num);            % 预测的歌曲
    origin = bidx(i, bst(i, :) > target_rate);  % 评分超过阈值的歌曲
    same = size(intersect(pred, origin), 2);    % 重合的歌曲数量
    precision(i) = same / size(origin, 2);
    fprintf("precise %d finished!", i);
end
precise_rate = mean(precision(~isnan(precision)));
