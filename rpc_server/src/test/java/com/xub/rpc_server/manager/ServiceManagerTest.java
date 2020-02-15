package com.xub.rpc_server.manager;

import com.xub.rpc_common.utils.ReflectUtil;
import com.xub.rpc_proto.entity.Request;
import com.xub.rpc_proto.entity.ServiceDescriptor;
import com.xub.rpc_server.entity.ServiceInstance;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    ServiceManager serviceManager;

    @Before
    public void init() {
        serviceManager = new ServiceManager();
        TestInterface testInterface = new TestInterface() {
            @Override
            public void hello() {
                System.out.println("hello");
            }
            @Override
            public void bye() {

            }
        };
        serviceManager.register(TestInterface.class, testInterface);
    }

    @Test
    public void register() {
        TestInterface testInterface = new TestInterface() {
            @Override
            public void hello() {
                System.out.println("hello");
            }

            @Override
            public void bye() {

            }
        };
        serviceManager.register(TestInterface.class, testInterface);
    }

    @Test
    public void lookup() {
        Method[] methods = ReflectUtil.getPublicMethods(TestInterface.class);
        Request request = new Request();
        ServiceDescriptor service = ServiceDescriptor.from(TestInterface.class,methods[0]);
        request.setService(service);
        ServiceInstance serviceInstance = serviceManager.lookup(request);
        assertNotNull(serviceInstance);
    }
}
