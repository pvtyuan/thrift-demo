package com.example.thriftdemo.sync;

import com.example.thriftdemo.RpcExample;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pvtyuan on 2017/9/25.
 */
public class SyncServer {
    private static Logger logger = LoggerFactory.getLogger(SyncServer.class);

    public static void main(String [] args) throws Exception {
        RpcExample.Processor processor = new RpcExample.Processor(new RpcExampleSyncService());

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
