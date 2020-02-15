package com.xub.rpc_transport.service;

/**
 * @author xub
 * @Name: TransportServer
 * @Description: TODO
 * 1、启动、监听
 * 2、接受请求
 * 3、关闭监听
 * @date 2020/2/14  22:47
 */
public interface TransportServer {

    /**
     * 启动、监听
     * @param port
     * @param handler
     */
    void init(int port, RequestHandler handler);

    /**
     * 接受请求
     */
    void start();

    /**
     * 关闭监听
     */
    void stop();
}
