package cn.bugstack.gateway.session;

import cn.bugstack.gateway.session.defaults.GenericReferenceSessionFactory;
import io.netty.channel.Channel;

//import java.nio.channels.Channel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GenericReferenceSessionFactoryBuilder {
    public Future<Channel> build(Configuration configuration) throws ExecutionException, InterruptedException {
        GenericReferenceSessionFactory genericReferenceSessionFactory = new GenericReferenceSessionFactory(configuration);
        return genericReferenceSessionFactory.openSession();
    }
}
