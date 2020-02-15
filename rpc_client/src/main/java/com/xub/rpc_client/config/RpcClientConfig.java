package com.xub.rpc_client.config;

import com.xub.rpc_client.selector.TransportSelector;
import com.xub.rpc_client.selector.impl.RandomTransportSelector;
import com.xub.rpc_codec.service.Decoder;
import com.xub.rpc_codec.service.Encoder;
import com.xub.rpc_codec.service.impl.JSONDecoder;
import com.xub.rpc_codec.service.impl.JSONEncoder;
import com.xub.rpc_proto.entity.Peer;
import com.xub.rpc_transport.service.TransportClient;
import com.xub.rpc_transport.service.impl.HTTPTransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author xub
 * @Name: RpcClientConfig
 * @Description: TODO
 * @date 2020/2/15  2:24
 */
@Data
public class RpcClientConfig {

    /**
     * 服务端传输类
     */
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;

    /**
     * 编码器
     */
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    /**
     * 解码器
     */
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    /**
     * server选择器
     */
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    /**
     * 客户端连接数量（默认：1）
     */
    private int connectCount = 1;

    /**
     * 客户端可连接的服务器列表（默认：连接本地的3000端口）
     */
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1",3000)
    );
}
