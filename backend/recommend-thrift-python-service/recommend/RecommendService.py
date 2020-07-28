import os

import numpy
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from thrift.transport import TSocket, TTransport

from recommend.api import RecommendService
from recommend.api.RecommendService import Iface
from recommend.util.Predict import Predict
from recommend.util.LoadData import loadData, sparseData
from recommend.util.AddData import addData
from recommend.util.CalculateSim import calculateSim

# 服务的服务器和端口
host = '127.0.0.1'
port = '9091'

class InputPathNotExistsException:
    """
    没有输入路径的异常
    """
    def __init__(self, msg="请先获取输出的分数文件！并命名为rating放到input文件夹！"):
        super(InputPathNotExistsException, self).__init__(msg)

class RecommendServiceHandler(Iface):

    userInputPath = None
    musicInputPath = None
    newUserIDPath = None
    newMusicIDPath = None
    userOutputPath = None
    musicOutputPath = None
    x = None
    sim = None

    def __init__(self):
        # 获取项目输入文件
        curPath = os.path.abspath(os.path.dirname(__file__))
        project_name = "backend"
        rootPath = curPath[0:curPath.find(project_name)+len(project_name)]
        self.userInputPath = os.path.join(rootPath, "output" + os.path.sep + "temp" + os.path.sep + "part-r-00000")
        self.musicInputPath = os.path.join(rootPath, "input" + os.path.sep + "data" + os.path.sep + "music_tag_list.dat")
        self.newMusicIDPath = os.path.join(rootPath, "input" + os.path.sep + "data" + os.path.sep + "new_music_id.dat")
        self.newUserIDPath = os.path.join(rootPath, "input" + os.path.sep + "data" + os.path.sep + "new_user_id.dat")
        self.userOutputPath = os.path.join(rootPath, "output" + os.path.sep + "ratings")
        self.musicOutputPath = os.path.join(rootPath, "output" + os.path.sep + "ratings")

    def _predictAll(self, flag="user"):
        row, column = self.sim.shape
        if flag == "user":
            path = self.userOutputPath
        else:
            path = self.musicOutputPath
        try:
            predict = Predict(self.x, self.sim)
            i = 0
            while i < row:
                numpy.savetxt(os.path.join(path, "%d.txt" % i), predict.predict(i))
            return True
        except Exception as _:
            print(_)
            return False

    def getUserRecommendDetail(self, uid):
        pass

    def getMusicRecommendDetail(self, mid):
        pass

    def recommendMusic(self):
        if not os.path.exists(self.musicInputPath):
            raise InputPathNotExistsException
        # 获取各个歌曲的稀疏矩阵
        data = sparseData(loadData(self.userInputPath))
        # 以第一次的数据计算出之间的相关度
        sim = calculateSim(data)
        # 通过第一次计算的数据填充矩阵
        self.x = addData(data, sim)
        # 根据填充之后的矩阵的相似度
        self.sim = calculateSim(self.x)
        if self._predictAll():
            os.remove(os.path.dirname(self.musicInputPath))
            return True
        return False

    def recommendUser(self):
        if not os.path.exists(self.userInputPath):
            raise InputPathNotExistsException
        # 获取各个用户的稀疏矩阵
        data = sparseData(loadData(self.userInputPath))
        # 以第一次的数据计算出之间的相关度
        sim = calculateSim(data)
        # 通过第一次计算的数据填充矩阵
        self.x = addData(data, sim)
        # 根据填充之后的矩阵的相似度
        self.sim = calculateSim(self.x)
        if self._predictAll():
            os.remove(os.path.dirname(self.userInputPath))
            return True
        return False

def main_thread():
    # 1. 创建 Thrift Server 的 ServeSocket
    serverSocket = TSocket.TServerSocket(host=host, port=port)
    # serverSocket = TSocket.TServerSocket(None, port="9091")
    # 2. 创建 Thrift Server 的 Transport = 帧传输方式
    transportFactory = TTransport.TFramedTransportFactory()
    # 3. 创建 Thrift Server 的 Protocol = 二进制传输协议
    protocolFactory = TBinaryProtocol.TBinaryProtocolFactory()
    # 4. 创建 Thrift Server 提供的处理方法
    handler = RecommendServiceHandler()
    # from message.api import MessageService
    processor = RecommendService.Processor(handler)
    # 5. 创建 Thrift Server, 指明如何处理，监听什么端口，传输方式，传输协议
    thriftServer = TServer.TSimpleServer(processor,
                                         serverSocket,
                                         transportFactory,
                                         protocolFactory)
    # 6. 启动 Thrift Server, 等待客户端的访问
    print("Python Thrift Server start ...")
    thriftServer.serve()
    print("Python Thrift Server stop ...")


if __name__ == '__main__':
    r = RecommendServiceHandler()
    r.recommendUser()