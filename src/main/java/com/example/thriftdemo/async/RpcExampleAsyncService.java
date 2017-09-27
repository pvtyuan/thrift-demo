package com.example.thriftdemo.async;

import com.example.thriftdemo.RpcExample;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * Created by Yuzhou on 2017/9/26.
 */
public class RpcExampleAsyncService implements RpcExample.AsyncIface {
    @Override
    public void add(int a, int b, AsyncMethodCallback<Integer> resultHandler) throws TException {
        resultHandler.onComplete(a + b);
//        resultHandler.onError(new Exception("test"));
    }
}
