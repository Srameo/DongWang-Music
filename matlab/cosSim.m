function sim = cosSim(x, y)
si = norm(x);
sj = norm(y);
if si * sj == 0
    sim = 0;
else
    sim = dot(x, y) / (si * sj);
end
end