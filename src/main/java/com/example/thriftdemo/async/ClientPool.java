package com.example.thriftdemo.async;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Created by pvtyuan on 2017/9/27.
 */
public class ClientPool {

    private GenericObjectPool<AsyncClient> pool;

    public ClientPool() {
        pool = new GenericObjectPool<>(new ClientFactory());
    }

    public AsyncClient borrowClient() throws Exception {
        return pool.borrowObject();
    }

    public void returnClient(AsyncClient client) {
        pool.returnObject(client);
    }

    private static class ClientFactory extends BasePooledObjectFactory<AsyncClient> {
        @Override
        public AsyncClient create() throws Exception {
            return new AsyncClient();
        }

        @Override
        public PooledObject<AsyncClient> wrap(AsyncClient asyncClient) {
            return new DefaultPooledObject<>(asyncClient);
        }
    }
}
