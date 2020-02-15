package com.xub.rpc_server.config;

import com.xub.rpc_codec.service.Decoder;
import com.xub.rpc_codec.service.Encoder;
import com.xub.rpc_codec.service.impl.JSONDecoder;
import com.xub.rpc_codec.service.impl.JSONEncoder;
import com.xub.rpc_transport.service.TransportServer;
import com.xub.rpc_transport.service.impl.HTTPTransportServer;
import lombok.Data;

/**
 * @author xub
 * @Name: RpcServerConfig server配置
 * @Description: TODO
 * @date 2020/2/15  0:21
 */
@Data
public class RpcServerConfig {

    /**
     * 服务端传输类
     */
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;

    /**
     * 编码器
     */
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    /**
     * 解码器
     */
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    /**
     * 服务端监听端口（默认：3000）
     */
    private int port = 3000;
}
