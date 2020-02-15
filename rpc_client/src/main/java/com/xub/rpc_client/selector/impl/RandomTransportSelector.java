package com.xub.rpc_client.selector.impl;

import com.xub.rpc_client.selector.TransportSelector;
import com.xub.rpc_common.utils.ReflectUtil;
import com.xub.rpc_proto.entity.Peer;
import com.xub.rpc_transport.service.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xub
 * @Name: RandomTransportSelector
 * @Description: TODO
 * @date 2020/2/15  2:09
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    /**
     * 初始化selector
     *
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectUtil.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server : {}", peer);
        }
    }

    /**
     * 选择一个Transport与server做交互
     *
     * @return
     */
    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    /**
     * 释放连接
     *
     * @param client
     */
    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    /**
     * 关闭
     */
    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}
