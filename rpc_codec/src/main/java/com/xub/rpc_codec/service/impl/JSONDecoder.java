package com.xub.rpc_codec.service.impl;

import com.alibaba.fastjson.JSON;
import com.xub.rpc_codec.service.Decoder;

/**
 * @author xub
 * @Name: JSONDecoder JSON解码器
 * @Description: TODO
 * @date 2020/2/14  22:07
 */
public class JSONDecoder implements Decoder {

    /**
     * 解码（基于json的反序列实现）
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
