package cn.bugstack.gateway.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.jboss.netty.channel.SimpleChannelHandler;

public abstract class BaseHandler<T> extends SimpleChannelInboundHandler<T> {
//    channelRead0还有一个好处就是你不用关心释放资源，因为源码中已经帮你释放了，所以如果你保存获取的信息的引用，是无效的~
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        session(ctx, ctx.channel(), msg);
    }
    protected abstract void session(ChannelHandlerContext ctx, final Channel channel, T request);
}
