import os

from recommend.api.RecommendService import Iface
from recommend.util.Predict import Predict
from recommend.util.LoadData import loadData, sparseData
from recommend.util.AddData import addData
from recommend.util.CalculateSim import calculateSim

class RecommendServiceHandler(Iface):

    inputPath = None
    x = None
    sim = None

    def __init__(self):
        curPath = os.path.abspath(os.path.dirname(__file__))
        project_name = "backend"
        rootPath = curPath[0:curPath.find(project_name)+len(project_name)]
        self.inputPath = os.path.join(rootPath, "input/rating")

    def _predictAll(self):
        row, column = self.sim.shape
        predict = Predict(self.x, self.sim)
        i = 0
        while i < row:
            predict.predict(i)


    def recommend(self):
        data = sparseData(loadData(self.inputPath))
        sim = calculateSim(data)
        self.x = addData(data, sim)
        self.sim = calculateSim(self.x)
        self._predictAll()

def main_thread():
    pass

if __name__ == '__main__':
    main_thread()