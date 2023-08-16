package cn.bugstack.gateway.socket;

import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.defaults.DefaultGatewaySessionFactory;
import cn.bugstack.gateway.socket.handlers.AuthorizationHandler;
import cn.bugstack.gateway.socket.handlers.GatewayServerHandler;
import cn.bugstack.gateway.socket.handlers.ProtocolDataHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

// ChannelInitializer的作用：用来进行设置出站解码器和入站编码器。
//使用场合：客户端和服务端之间消息的传递包含特殊字符需要统一编码格式时，在客户端和服务端加上ChannelInitializer继承类
// 重写initChannel方法，设置编码和解码格式。但当传输的数据不包含特殊字符例如报文时，客户端和服务端不需要写ChannelInitializer继承类。

public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final DefaultGatewaySessionFactory gatewaySessionFactory;
    private final Configuration configuration;

    public GatewayChannelInitializer( Configuration configuration,DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline line = channel.pipeline();
        line.addLast(new HttpRequestDecoder());
        line.addLast(new HttpResponseEncoder());
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        line.addLast(new GatewayServerHandler(configuration));
        line.addLast(new AuthorizationHandler(configuration));
        line.addLast(new ProtocolDataHandler(gatewaySessionFactory));
    }
}
