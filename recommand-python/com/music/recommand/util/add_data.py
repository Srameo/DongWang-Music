def addData(x, sim):
    X = sim * x
    row, column = sim.shape
    i = 0
    while i < row:
        sumsim = sim[i, :].sum()
        if sumsim == 0:
            i += 1
            continue
        X[i, :] /= sumsim
        i += 1
        print("add data finish: {:.6%}".format(i / row))
    return X