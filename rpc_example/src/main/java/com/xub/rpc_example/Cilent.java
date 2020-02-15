package com.xub.rpc_example;

import com.xub.rpc_client.RpcClient;
import com.xub.rpc_example.service.CalcService;

/**
 * @author xub
 * @Name: Cilent
 * @Description: TODO
 * @date 2020/2/15  10:58
 */
public class Cilent {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int add = service.add(1, 2);
        int minus = service.minus(10, 4);
        System.out.println(add);
        System.out.println(minus);
    }
}
