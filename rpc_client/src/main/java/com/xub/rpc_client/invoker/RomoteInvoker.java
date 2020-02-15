package com.xub.rpc_client.invoker;

import com.xub.rpc_client.selector.TransportSelector;
import com.xub.rpc_codec.service.Decoder;
import com.xub.rpc_codec.service.Encoder;
import com.xub.rpc_proto.entity.Request;
import com.xub.rpc_proto.entity.Response;
import com.xub.rpc_proto.entity.ServiceDescriptor;
import com.xub.rpc_proto.enums.ResponseCodeEnum;
import com.xub.rpc_transport.service.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xub
 * @Name: RomoteInvoker 调用远程服务的代理类
 * @Description: TODO
 * @date 2020/2/15  2:41
 */
@Slf4j
public class RomoteInvoker implements InvocationHandler {

    private Class clazz;

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

    public RomoteInvoker(Class clazz,
                         Encoder encoder,
                         Decoder decoder,
                         TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        //1、创建连接对象
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParams(args);
        //2、调用远程服务
        Response response = invokeRomote(request);
        //3、判断返回结果
        if (!ResponseCodeEnum.SUCCESS.getCode().equals(response.getCode())) {
            throw new IllegalStateException("fail to invoke remote: " + request);
        }
        return response.getData();
    }

    private Response invokeRomote(Request request) {
        TransportClient client = null;
        Response response = new Response();
        try {
            //获取连接
            client = selector.select();
            //编码
            byte[] outBytes = encoder.encode(request);
            //发送信息
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            //获取返回信息
            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            //解码
            response = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response = Response.fail(new StringBuilder().append("RpcClient got error:")
                    .append(e.getClass())
                    .append(":")
                    .append(e.getMessage()).toString());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return response;
    }
}
