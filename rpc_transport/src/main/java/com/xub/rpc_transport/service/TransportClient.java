package com.xub.rpc_transport.service;

import com.xub.rpc_proto.entity.Peer;

import java.io.InputStream;

/**
 * @author xub
 * @Name: TransportClient
 * @Description: TODO
 * 1、创建连接
 * 2、发送数据，并且等待响应
 * 3、关闭连接
 * @date 2020/2/14  22:42
 */
public interface TransportClient {

    /**
     * 创建连接
     * @param peer
     */
    void connect(Peer peer);

    /**
     * 发送数据，并且等待响应
     * @param data
     * @return
     */
    InputStream write(InputStream data);

    /**
     * 关闭连接
     */
    void close();
}
