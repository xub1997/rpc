package com.xub.rpc_proto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xub
 * @Name: Peer 表示网络传输的一个端点
 * @Description: TODO
 * @date 2020/2/14  21:01
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Peer {

    /**
     * 主机ip
     */
    private String host;

    /**
     * 端口
     */
    private int port;
}
