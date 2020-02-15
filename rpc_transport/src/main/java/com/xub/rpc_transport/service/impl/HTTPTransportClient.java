package com.xub.rpc_transport.service.impl;

import com.xub.rpc_proto.entity.Peer;
import com.xub.rpc_transport.service.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author xub
 * @Name: HTTPTransportClient
 * @Description: TODO
 * @date 2020/2/14  22:54
 */
public class HTTPTransportClient implements TransportClient {

    private String url;

    /**
     * 创建连接
     *
     * @param peer
     */
    @Override
    public void connect(Peer peer) {
        this.url = new StringBuilder().append("http://")
                .append(peer.getHost())
                .append(":")
                .append(peer.getPort()).toString();
    }

    /**
     * 发送数据，并且等待响应
     *
     * @param data
     * @return
     */
    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            IOUtils.copy(data, conn.getOutputStream());
            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                return conn.getInputStream();
            }else {
                return conn.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 关闭连接
     */
    @Override
    public void close() {

    }
}
