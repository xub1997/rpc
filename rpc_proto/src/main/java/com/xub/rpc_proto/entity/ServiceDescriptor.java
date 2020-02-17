package com.xub.rpc_proto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xub
 * @Name: ServiceDescriptor 表示服务
 * @Description: TODO
 * @date 2020/2/14  21:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ServiceDescriptor {

    /**
     * 类名
     */
    private String clazz;

    /**
     * 方法名
     */
    private String method;

    /**
     * 返回值类型
     */
    private String returnType;

    /**
     * 参数类型
     */
    private String[] paramTypes;

    /**
     * 从类名和方法名快速提取服务
     *
     * @param clazz
     * @param method
     * @return
     */
    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor descriptor = new ServiceDescriptor();
        descriptor.setClazz(clazz.getName());
        descriptor.setMethod(method.getName());
        descriptor.setReturnType(method.getReturnType().getName());
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] paramTypes = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            paramTypes[i] = parameterTypes[i].getName();
        }
        return descriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceDescriptor that = (ServiceDescriptor) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", paramTypes=" + Arrays.toString(paramTypes) +
                '}';
    }
}
