function sim = personSim(x, y)
mx = mean(x(x>0));
my = mean(y(y>0));
if isnan(mx) || isnan(my)
    sim = 0;
else
    x(x > 0) = x(x > 0) - mx;
    y(y > 0) = y(y > 0) - my;
    sim = dot(x, y) / (norm(x) * norm(y));
end
end