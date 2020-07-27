import numpy


class Predict:

    x = None
    sim = None
    mean_rate = None
    sorted_sim_arg = None

    def __init__(self, x, sim):
        self.x = x
        self.sim = sim
        self.sorted_sim_arg = sim.argsort()[:, ::-1]
        self.mean_rate = x.mean(axis=1)

    def predict(self, u, k=50):
        sorted_sim_arg = self.sorted_sim_arg[u, 0:k]
        sorted_sim = self.sim[u, sorted_sim_arg][:, 0:k]
        mu = self.mean_rate[u]
        if sorted_sim.sum() != 0:
            mean = self.mean_rate[sorted_sim_arg][0]
            index = sorted_sim_arg.tolist()[0]
            rate = self.x[index]
            delta = rate - mean
            p = mu + sorted_sim * delta / sorted_sim.sum()
        else:
            p = numpy.zeros((1, self.x.shape[1]))
        return p

