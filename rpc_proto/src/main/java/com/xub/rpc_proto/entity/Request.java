package com.xub.rpc_proto.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xub
 * @Name: Request 表示PRC的一个请求
 * @Description: TODO
 * @date 2020/2/14  21:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Request {

    /**
     * 服务
     */
    private ServiceDescriptor service;

    /**
     * 参数
     */
    private Object[] params;
}
