package com.music.thrift.message;

import org.apache.thrift.TException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TestMessageService {

    static String host = "127.0.0.1";
    static int port = 9090;

    public static void main(String[] args) throws TException {
        // RPC - Socket\Transport\Protocol
        // 1. 声明一个 Socket 用来连接 ServerSocket
        TSocket socket = new TSocket(host, port, 3000);
        // 2. 指定生成一个传输方式对象 -- 基于 Socket 链接创建一个帧传输对象
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return;
        }
        // 3. 指定传输协议
        TProtocol protocol = new TBinaryProtocol(transport);

        // 4. 获取服务客户端
        TServiceClient result = null;
        // 判断服务类型，并根据服务类型，返回不同客户端
        result = new MessageService.Client(protocol);
        MessageService.Iface messageService = (MessageService.Iface) result;
        messageService.sendEmailMessage("514152738@qq.com","123456");
    }
}
