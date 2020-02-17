package com.xub.rpc_client;

import com.xub.rpc_client.config.RpcClientConfig;
import com.xub.rpc_client.invoker.RomoteInvoker;
import com.xub.rpc_client.selector.TransportSelector;
import com.xub.rpc_codec.service.Decoder;
import com.xub.rpc_codec.service.Encoder;
import com.xub.rpc_common.utils.ReflectUtil;
import com.xub.rpc_transport.service.TransportClient;

import java.lang.reflect.Proxy;

/**
 * @author xub
 * @Name: RpcClient
 * @Description: TODO
 * @date 2020/2/15  2:31
 */
public class RpcClient {

    /**
     * 配置信息
     */
    private RpcClientConfig config;

    /**
     * 传输服务器
     */
    private TransportClient transportClient;

    /**
     * 编码器
     */
    private Encoder encoder;

    /**
     * 解码器
     */
    private Decoder decoder;

    /**
     * server选择器
     */
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectUtil.newInstance(config.getEncoderClass());
        this.decoder = ReflectUtil.newInstance(config.getDecoderClass());
        this.transportClient = ReflectUtil.newInstance(config.getTransportClass());
        this.selector = ReflectUtil.newInstance(config.getSelectorClass());
        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    /**
     * Java 中的动态代理，实际上指的就是反射包java.lang.reflect下的 类 Proxy
     * 获取远程代理（通过动态代理的方式，在RomoteInvoker调用invoke方法获得相应方法的结果）
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RomoteInvoker(clazz, encoder, decoder, selector)
        );
    }
}
