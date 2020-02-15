package com.xub.rpc_codec.service;

/**
 * @author xub
 * @Name: Decoder 反序列化（byte数组转成对象）
 * @Description: TODO
 * @date 2020/2/14  22:00
 */
public interface Decoder {

    /**
     * 解码
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> clazz);
}
