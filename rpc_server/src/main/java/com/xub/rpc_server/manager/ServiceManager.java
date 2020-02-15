package com.xub.rpc_server.manager;

import com.xub.rpc_common.utils.ReflectUtil;
import com.xub.rpc_proto.entity.Request;
import com.xub.rpc_proto.entity.ServiceDescriptor;
import com.xub.rpc_server.entity.ServiceInstance;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xub
 * @Name: ServiceManager
 * @Description: TODO
 * @date 2020/2/15  0:39
 */
@Slf4j
public class ServiceManager {

    /**
     * 服务管理中心（服务及对应的实例）
     */
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册
     *
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, Object bean) {
        //获取该类所有的public方法
        Method[] methods = ReflectUtil.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            //服务
            ServiceDescriptor descriptor = ServiceDescriptor.from(interfaceClass, method);
            //对应实例
            ServiceInstance instance = new ServiceInstance(bean, method);
            services.put(descriptor, instance);
            log.info("register service: {}, {}", descriptor.getClazz(), descriptor.getMethod());
        }
    }

    /**
     * 获取服务实例
     *
     * @param request
     * @return
     */
    public ServiceInstance lookup(Request request) {
        ServiceDescriptor service = request.getService();
        return services.get(service);
    }
}
