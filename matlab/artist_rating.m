%% ===== load Data ===== %%

A = load("user_artists.dat");
a=sparse(A(:,1)', A(:,2)', A(:,3)');%第一个是行，第二个是列，第三个是具体的数
b = full(a);

%% ===== compute Rating ===== %%
[row, ~] = size(b);
for i = 1:row
    temp = b(i, b(i,:) > 0);
    m = mean(temp);
    s = std(temp);
    b(i, b(i,:) > 0) = sigmoid((temp - m) / s);
end

%% ===== compute person ===== %%