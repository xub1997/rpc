package com.xub.rpc_codec.service;

/**
 * @author xub
 * @Name: Encoder 序列化（对象转成byte数组）
 * @Description: TODO
 * @date 2020/2/14  22:00
 */
public interface Encoder {

    /**
     *编码
     * @param object
     * @return
     */
    byte[] encode(Object object);
}
