import pandas
import sparse

def loadData(path):
    data = pandas.read_csv(path, sep="\t", header=None)
    return data

def sparseData(data):
    pass