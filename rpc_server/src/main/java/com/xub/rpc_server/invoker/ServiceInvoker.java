package com.xub.rpc_server.invoker;

import com.xub.rpc_common.utils.ReflectUtil;
import com.xub.rpc_proto.entity.Request;
import com.xub.rpc_server.entity.ServiceInstance;

/**
 * @author xub
 * @Name: ServiceInvoker 服务调用者
 * @Description: TODO
 * @date 2020/2/15  1:19
 */
public class ServiceInvoker {

    /**
     * 调用服务
     *
     * @param service
     * @param request
     * @return
     */
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectUtil.invoke(service.getTarget(),
                service.getMethod(),
                request.getParams());
    }
}
