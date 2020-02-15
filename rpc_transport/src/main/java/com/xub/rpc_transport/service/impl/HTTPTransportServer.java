package com.xub.rpc_transport.service.impl;

import com.xub.rpc_transport.service.RequestHandler;
import com.xub.rpc_transport.service.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xub
 * @Name: HTTPTransportServer
 * @Description: TODO
 * @date 2020/2/15  0:07
 */
@Slf4j
public class HTTPTransportServer implements TransportServer {

    private RequestHandler handler;

    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);
        //servlet 接收请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);
        //自定义HttpServlet，交给ServletHolder处理
        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    class RequestServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            log.info("client connect");
            ServletInputStream input = request.getInputStream();
            ServletOutputStream out = response.getOutputStream();
            if (handler != null) {
                handler.onRequest(input, out);
            }
            out.flush();
        }
    }
}
