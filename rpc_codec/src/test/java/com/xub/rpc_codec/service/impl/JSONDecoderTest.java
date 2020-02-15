package com.xub.rpc_codec.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONDecoderTest {

    @Test
    public void decode() {
        JSONEncoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("张三");
        bean.setAge(23);
        byte[] encode = encoder.encode(bean);
        System.out.println(bean);
        assertNotNull(encode);
        JSONDecoder decoder = new JSONDecoder();
        TestBean decode = decoder.decode(encode, TestBean.class);
        System.out.println(decode);
        assertEquals(bean,decode);
    }
}
