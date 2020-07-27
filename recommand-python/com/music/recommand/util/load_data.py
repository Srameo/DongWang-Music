import pandas
from scipy import sparse
import numpy

def loadData(path):
    data = pandas.read_csv(path, sep="\t", header=None)
    return numpy.matrix(data)

def sparseData(data, row=2100, column=18745):
    data[:, 0] = data[:, 0] - 1
    data[:, 1] = data[:, 1] - 1
    return sparse.coo_matrix((data[:, 2].flatten()[0].tolist()[0],
                              (data[:, 0].flatten()[0].tolist()[0], data[:, 1].flatten()[0].tolist()[0])),
                              shape=(row, column)).tocsr()