package com.xub.rpc_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author xub
 * @Name: ServiceInstance 表示一个具体服务
 * @Description: TODO
 * @date 2020/2/15  0:36
 */
@Data
@AllArgsConstructor
public class ServiceInstance {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 目标对象方法（调用方法）
     */
    private Method method;
}
