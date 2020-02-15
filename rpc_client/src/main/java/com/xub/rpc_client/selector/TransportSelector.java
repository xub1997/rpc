package com.xub.rpc_client.selector;

import com.xub.rpc_proto.entity.Peer;
import com.xub.rpc_transport.service.TransportClient;

import java.util.List;

/**
 * @author xub
 * @Name: TransportSelector server选择器
 * @Description: TODO
 * @date 2020/2/15  2:01
 */
public interface TransportSelector {

    /**
     * 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个Transport与server做交互
     * @return
     */
    TransportClient select();

    /**
     * 释放连接
     * @param client
     */
    void release(TransportClient client);

    /**
     * 关闭
     */
    void close();
}
