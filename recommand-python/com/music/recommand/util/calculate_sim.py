def cosSim(x):
    mod_x = (x * x.transpose())[0, 0]
    if mod_x == 0:
        return 1
    return 1 / (mod_x) ** 0.5

def calculateSim(data):
    row, column = data.shape
    sim = data * data.transpose()
    i = 0
    while i < row:
        mo = cosSim(data[i, :])
        sim[i, :] *= mo
        sim[:, i] *= mo
        i += 1
        print("calculate sim finished: {:.6%}".format(i / row))
    return sim
