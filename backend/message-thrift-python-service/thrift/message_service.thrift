# 声明 Java 包名
namespace java com.music.thrift.message
# 声明 python 包名
namespace py message.api

# 定义服务，给出服务名称
service MessageService {
    # 发送邮件，return: bool, input: email, content
    bool sendEmailMessage(1:string email, 2:string message);
}