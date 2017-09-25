package com.example.thriftdemo.sync;

import com.example.thriftdemo.RpcExample;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pvtyuan on 2017/9/25.
 */
public class SyncClient {
    private static Logger logger = LoggerFactory.getLogger(SyncClient.class);

    public static void main(String[] args) throws Exception {

        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 8080));
        transport.open();

        TProtocol protocol = new TCompactProtocol(transport);
        RpcExample.Client client = new RpcExample.Client(protocol);

        logger.info("" + client.add(1, 2));
        logger.info("" + client.add(2, 2));
        transport.close();
    }
}
