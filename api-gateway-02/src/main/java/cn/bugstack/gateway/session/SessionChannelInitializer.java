package cn.bugstack.gateway.session;

import cn.bugstack.gateway.session.handlers.SessionServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 网络协议
 * Netty的通信会为每个用户建立Channel管道 对应的ChannelID唯一
 * HttpRequestDecoder() 编码器
 * HttpResponseEncoder() 解码器
 * HttpObjectAggregator(1024 * 1024) 处理get请求以外的post请求
 * SessionServerHandler自定义的绘画处理
 */
public class SessionChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final Configuration configuration;

    public SessionChannelInitializer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline line = channel.pipeline();
        line.addLast(new HttpRequestDecoder());
        line.addLast(new HttpResponseEncoder());
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        line.addLast(new SessionServerHandler(configuration));
    }

}
