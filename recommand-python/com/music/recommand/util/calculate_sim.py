import os

def cosSim(x, y):
    mod_x = (x * x.transpose())[0, 0]
    mod_y = (y * y.transpose())[0, 0]
    if mod_x * mod_y == 0:
        return 1
    return (mod_x * mod_y) ** 0.5


def calculateSim(data):
    row, column = data.shape
    sim = data * data.transpose()
    indices = sim.indices
    i = 0
    length = sim.count_nonzero()
    while i < length:
        r = int(indices[i] / row)
        c = indices[i] % row
        sim[r, c] /= cosSim(data[r, :], data[c, :])
        i += 1
        os.system('clear')
        print("finished: {:.6%}".format(i / length))
    return sim
