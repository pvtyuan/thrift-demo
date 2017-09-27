package com.example.thriftdemo.async;

import com.example.thriftdemo.RpcExample;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Yuzhou on 2017/9/26.
 */
public class AsyncServer {

    private static Logger logger = LoggerFactory.getLogger(AsyncServer.class);

    public static void main(String [] args) throws Exception {

        TProcessor processor = new RpcExample.AsyncProcessor<>(new RpcExampleAsyncService());
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8080);
        TThreadedSelectorServer.Args serverArgs = new TThreadedSelectorServer.Args(serverSocket);
        serverArgs.processor(processor);
        serverArgs.transportFactory(new TFastFramedTransport.Factory());
        serverArgs.protocolFactory(new TCompactProtocol.Factory());

        TServer server = new TThreadedSelectorServer(serverArgs);
        logger.info("server start");
        server.serve();
    }

}
