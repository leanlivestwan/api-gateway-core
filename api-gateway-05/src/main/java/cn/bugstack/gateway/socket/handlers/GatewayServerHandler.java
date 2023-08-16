package cn.bugstack.gateway.socket.handlers;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.session.GatewaySession;
import cn.bugstack.gateway.session.defaults.DefaultGatewaySessionFactory;
import cn.bugstack.gateway.socket.BaseHandler;
import cn.bugstack.gateway.socket.agreement.RequestParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

//import java.util.logging.Logger;

public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {
    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);
    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewayServerHandler(DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    // FullHttpRequest Content-Type为 x-www-form-urlencoded的类型，需要从中获取请求参数信息
    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求 uri：{} method：{}", request.uri(), request.method());

        Map<String, Object> requestObj = new RequestParser(request).parse();

        // 返回信息控制，简单处理
        String uri = request.uri();
        int idx = uri.indexOf("?");
        uri = idx > 0 ? uri.substring(0, idx) : uri;
        if (uri.equals("/favicon.ico")) return;
        // 会话
        GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
        IGenericReference reference = gatewaySession.getMapper();


        String result = reference.$invoke(requestObj) + " " + System.currentTimeMillis();

        // 返回信息处理
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

        // 设置回写的数据
        response.content().writeBytes(JSON.toJSONBytes(result, SerializerFeature.PrettyFormat));
        // 设置头部的信息
        HttpHeaders heads = response.headers();
        // 返回内容的类型
        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + "; charset=UTF-8");
        // 响应体的长度
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        // 配置持久连接
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        // 配置跨域访问
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

        channel.writeAndFlush(response);
    }
}
