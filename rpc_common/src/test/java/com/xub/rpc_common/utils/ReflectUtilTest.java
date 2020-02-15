package com.xub.rpc_common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectUtilTest {

    @Test
    public void newInstance() {
        TestClass instance = ReflectUtil.newInstance(TestClass.class);
        assertNotNull(instance);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectUtil.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);
        assertEquals("b",methods[0].getName());

    }

    @Test
    public void invoke() {
        Method[] methods = ReflectUtil.getPublicMethods(TestClass.class);
        TestClass instance = ReflectUtil.newInstance(TestClass.class);
        Object invoke = ReflectUtil.invoke(instance, methods[0]);
        assertEquals("b",invoke);
    }
}
