package com.xub.rpc_example;

import com.xub.rpc_example.service.CalcService;
import com.xub.rpc_example.service.impl.CalcServiceImpl;
import com.xub.rpc_server.RpcServer;

/**
 * @author xub
 * @Name: Server
 * @Description: TODO
 * @date 2020/2/15  10:58
 */
public class Server {

    public static void main(String[] args){
        RpcServer server = new RpcServer();
        server.register(CalcService.class,new CalcServiceImpl());
        server.start();
    }
}
