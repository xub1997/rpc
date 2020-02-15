package com.xub.rpc_server;

import com.xub.rpc_codec.service.Decoder;
import com.xub.rpc_codec.service.Encoder;
import com.xub.rpc_common.utils.ReflectUtil;
import com.xub.rpc_proto.entity.Request;
import com.xub.rpc_proto.entity.Response;
import com.xub.rpc_server.config.RpcServerConfig;
import com.xub.rpc_server.entity.ServiceInstance;
import com.xub.rpc_server.invoker.ServiceInvoker;
import com.xub.rpc_server.manager.ServiceManager;
import com.xub.rpc_transport.service.RequestHandler;
import com.xub.rpc_transport.service.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author xub
 * @Name: RpcServer
 * @Description: TODO
 * @date 2020/2/15  1:25
 */
@Slf4j
public class RpcServer {

    /**
     * 配置信息
     */
    private RpcServerConfig config;

    /**
     * 传输服务器
     */
    private TransportServer transportServer;

    /**
     * 编码器
     */
    private Encoder encoder;

    /**
     * 解码器
     */
    private Decoder decoder;

    /**
     * 服务管理中心
     */
    private ServiceManager serviceManager;

    /**
     * 服务调用者
     */
    private ServiceInvoker serviceInvoker;

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request = {}", request);
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object returnValue = serviceInvoker.invoke(serviceInstance, request);
                response = Response.success("操作成功",returnValue);
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                response = Response.fail(new StringBuilder().append("RpcServer got error:")
                        .append(e.getClass().getName())
                        .append(e.getMessage()).toString());
            }finally {
                byte[] outBytes = encoder.encode(response);
                try {
                    toResp.write(outBytes);
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.transportServer = ReflectUtil.newInstance(config.getTransportClass());
        this.transportServer.init(config.getPort(), this.handler);
        this.encoder = ReflectUtil.newInstance(config.getEncoderClass());
        this.decoder = ReflectUtil.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    /**
     * 注册
     *
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, Object bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.transportServer.start();
    }

    public void stop() {
        this.transportServer.stop();
    }
}
