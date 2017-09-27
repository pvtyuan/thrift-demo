package com.example.thriftdemo.async;

import com.example.thriftdemo.RpcExample;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Yuzhou on 2017/9/27.
 */
public class AsyncClient {

    private static Logger logger = LoggerFactory.getLogger(AsyncClient.class);

    public static void main(String[] args) throws Exception {
        TNonblockingTransport transport = new TNonblockingSocket("localhost", 8080);

        RpcExample.AsyncClient client = new RpcExample.AsyncClient(new TCompactProtocol.Factory(),
                new TAsyncClientManager(), transport);

        client.add(1, 2, new AsyncMethodCallback<Integer>() {
            @Override
            public void onComplete(Integer response) {
                logger.info("" + response);
            }

            @Override
            public void onError(Exception exception) {
                logger.error("ops", exception);
            }
        });
        Thread.sleep(200);
        client.add(2, 2, new AsyncMethodCallback<Integer>() {
            @Override
            public void onComplete(Integer response) {
                logger.info("" + response);
            }

            @Override
            public void onError(Exception exception) {
                logger.error("ops", exception);
            }
        });
        Thread.sleep(200);
        transport.close();
    }
}
