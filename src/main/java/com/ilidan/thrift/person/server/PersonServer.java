package com.ilidan.thrift.person.server;

import com.ilidan.thrift.person.PersonService;
import com.ilidan.thrift.person.impl.PersonServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

public class PersonServer {

    public static void main(String[] args) throws Exception {

        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        arg.protocolFactory(new TCompactProtocol.Factory());//协议层
        arg.transportFactory(new TFastFramedTransport.Factory());//传输层
        arg.processorFactory(new TProcessorFactory(processor));
        THsHaServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started!");
        server.serve();
    }

}
