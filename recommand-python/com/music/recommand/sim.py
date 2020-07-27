import numpy
from scipy.sparse import save_npz

from util.calculate_sim import calculateSim
from util.load_data import loadData, sparseData

if __name__ == '__main__':
    # data = loadData("/root/rating")
    data = loadData("/Users/jinxin/Desktop/project/my/DongWang-Music/recommand-python/input/data/rating")
    # print(data[:, 0].flatten()[0].tolist()[0])
    s_data = sparseData(data)
    # print(s_data.count_nonzero())
    save_npz('"/Users/jinxin/Desktop/project/my/DongWang-Music/recommand-python/output/filename.npz', calculateSim(s_data))
    print("all finished!")
