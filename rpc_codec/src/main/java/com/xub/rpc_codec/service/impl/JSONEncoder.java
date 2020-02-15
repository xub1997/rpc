package com.xub.rpc_codec.service.impl;

import com.alibaba.fastjson.JSON;
import com.xub.rpc_codec.service.Encoder;

/**
 * @author xub
 * @Name: JSONEncoder JSON编码器
 * @Description: TODO
 * @date 2020/2/14  22:07
 */
public class JSONEncoder implements Encoder {

    /**
     * 编码（基于JSON 的序列化实现）
     *
     * @param object
     * @return
     */
    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }
}
