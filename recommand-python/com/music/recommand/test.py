import numpy
from scipy import sparse

from com.music.recommand.util.add_data import addData
from com.music.recommand.util.calculate_sim import calculateSim
from com.music.recommand.util.load_data import loadData, sparseData
from com.music.recommand.util.predict import Predict

if __name__ == '__main__':
    X = sparse.load_npz("/Users/jinxin/Desktop/project/my/DongWang-Music/recommand-python/output/X.npz")
    sim2 = sparse.load_npz("/Users/jinxin/Desktop/project/my/DongWang-Music/recommand-python/output/sim2.npz").todense()
    predict = Predict(X, sim2)
    ans_list = []
    for i in range(2100):
        pre = predict.predict(i).argsort()[:, ::-1][:, 0:30]
        ans_list.append(pre)
        print("predict: {:.2%}".format(i/2100))


