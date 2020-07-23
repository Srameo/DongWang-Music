# encoding:utf8
# 导入邮件发送所需模块
import smtplib
from email.mime.text import MIMEText
from email.header import Header
# 导入 thrift 所需模块
from thrift.server import TServer
from thrift.transport import TSocket, TTransport
from thrift.protocol import TBinaryProtocol

from message.api import MessageService

# 服务的服务器和端口
host = '127.0.0.1'
port = '9090'

# 给出邮件发送的信息
sender = 'srameo@163.com'
authCode = 'ARFOJLGXYAWWQQBD'

# 生成一个py类
class MessageServiceHandler:

    # 给出发送短信验证码的具体实现
    def sendMobileMessage(self, mobile, message):
        print("sendMobileMessage: " + mobile + ", message: " + message)
        return True

    # 给出发送邮件验证码的具体实现
    def sendEmailMessage(self, email, message):
        # 0. 打印信息
        print("sendMessageMessage: " + email + ", message: " + message)
        # 1. 创建消息对象，设置相应属性
        messageObj = MIMEText(message, 'plain', 'utf8')
        # 2. 设置消息对象的发送者、接受者、主题
        messageObj['From'] = sender
        messageObj['To'] = email
        messageObj['Subject'] = Header("验证码", 'utf8')
        # 3. 创建邮件发送对象，并设置参数
        try:
            smtpObj = smtplib.SMTP('smtp.163.com')
            smtpObj.login(sender, authCode)
            smtpObj.sendmail(sender, [email, ], messageObj.as_string())
            print("send email success!")
            return True
        except smtplib.SMTPException as e:
            print("send email failed!")
            print(e)
            return False

if __name__ == "__main__":
    # 1. 创建 Thrift Server 的 ServeSocket
    serverSocket = TSocket.TServerSocket(host=host, port=port)
    # serverSocket = TSocket.TServerSocket(None, port="9090")
    # 2. 创建 Thrift Server 的 Transport = 帧传输方式
    transportFactory = TTransport.TFramedTransportFactory()
    # 3. 创建 Thrift Server 的 Protocol = 二进制传输协议
    protocolFactory = TBinaryProtocol.TBinaryProtocolFactory()
    # 4. 创建 Thrift Server 提供的处理方法
    handler = MessageServiceHandler()
    # from message.api import MessageService
    processor = MessageService.Processor(handler)
    # 5. 创建 Thrift Server, 指明如何处理，监听什么端口，传输方式，传输协议
    thriftServer = TServer.TSimpleServer(processor,
                                         serverSocket,
                                         transportFactory,
                                         protocolFactory)
    # 6. 启动 Thrift Server, 等待客户端的访问
    print("Python Thrift Server start ...")
    thriftServer.serve()
    print("Python Thrift Server stop ...")
