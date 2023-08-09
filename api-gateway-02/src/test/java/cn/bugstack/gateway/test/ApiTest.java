package cn.bugstack.gateway.test;

import cn.bugstack.gateway.session.Configuration;
//import cn.bugstack.gateway.session.Ge;
//import cn.bugstack.gateway.session.session.GenericReferenceSessionFactoryBuilder;
import cn.bugstack.gateway.session.GenericReferenceSessionFactoryBuilder;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    /**
     * 测试：http://localhost:7397/sayHi
     */
    @Test
    public void test_GenericReference() throws InterruptedException, ExecutionException {
        Configuration configuration = new Configuration();
        configuration.addGenericReference("api-gateway-test", "cn.bugstack.gateway.rpc.IActivityBooth", "sayHi");
        GenericReferenceSessionFactoryBuilder builder = new GenericReferenceSessionFactoryBuilder();
        Future<Channel> future = builder.build(configuration);
        logger.info("服务启动完成 {}", future.get().id());
        Thread.sleep(Long.MAX_VALUE);
    }

}