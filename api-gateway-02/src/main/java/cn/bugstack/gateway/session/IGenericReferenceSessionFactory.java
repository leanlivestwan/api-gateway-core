package cn.bugstack.gateway.session;

//import java.nio.channels.Channel;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface IGenericReferenceSessionFactory {
    Future<Channel> openSession() throws ExecutionException, InterruptedException;
}
