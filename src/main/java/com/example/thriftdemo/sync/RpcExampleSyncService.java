package com.example.thriftdemo.sync;

import com.example.thriftdemo.RpcExample;
import org.apache.thrift.TException;

/**
 * Created by pvtyuan on 2017/9/25.
 */
public class RpcExampleSyncService implements RpcExample.Iface {

    @Override
    public int add(int a, int b) throws TException {
        return a + b;
    }
}
