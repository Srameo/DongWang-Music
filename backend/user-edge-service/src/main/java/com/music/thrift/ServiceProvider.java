package com.music.thrift;

import com.music.thrift.message.MessageService;
import com.music.thrift.recommend.RecommendService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Value("${thrift.message.ip}")
    private String messageServerIP;
    @Value("${thrift.message.port}")
    private int messageServerPort;
    @Value("${thrift.recommend.ip}")
    private String recommendServerIP;
    @Value("${thrift.recommend.port}")
    private int recommendServerPort;

    private enum ServiceType{
        MESSAGE,
        RECOMMEND
    }

    public <T> T getService(String ip, int port, ServiceType serviceType){
        TSocket socket = new TSocket(ip, port, 3000);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);

        TServiceClient result = null;
        switch (serviceType){
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
            case RECOMMEND:
                result = new RecommendService.Client(protocol);
        }

        return (T)result;
    }

    public MessageService.Client getMessageService(){
        return getService(messageServerIP, messageServerPort, ServiceType.MESSAGE);
    }

    public RecommendService.Client getRecommendService() {
        return getService(recommendServerIP, recommendServerPort, ServiceType.RECOMMEND);
    }

}
