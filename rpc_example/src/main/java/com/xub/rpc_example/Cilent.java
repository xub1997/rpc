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
        //通过JDK动态代理的方式进行获取（在获取结果的时候通过http获取结果实现rpc效果）
        CalcService service = client.getProxy(CalcService.class);
        int add = service.add(1, 2);
        int minus = service.minus(10, 4);
        System.out.println(add);
        System.out.println(minus);
    }
}
