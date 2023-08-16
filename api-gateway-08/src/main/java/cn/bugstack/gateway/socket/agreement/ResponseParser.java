package cn.bugstack.gateway.socket.agreement;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.*;

public class ResponseParser {

    public DefaultFullHttpResponse parse(Object result) {
        // 返回信息处理
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        // 设置回写数据
        response.content().writeBytes(JSON.toJSONString(result).getBytes());
        // 头部信息设置
        HttpHeaders heads = response.headers();
        // 返回内容类型
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
        return response;
    }


}
