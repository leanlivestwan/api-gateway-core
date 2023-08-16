package cn.bugstack.gateway.test;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛化调用测试
 * 官网案例：https://dubbo.apache.org/zh/docs/advanced/generic-reference/
 */
public class RPCTest {

    @Test
    public void test_rpc() {

        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-gateway-test");
        application.setQosEnable(false);

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("cn.bugstack.gateway.rpc.IActivityBooth");
        reference.setVersion("1.0.0");
        reference.setGeneric("true");

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(application)
                .registry(registry)
                .reference(reference)
                .start();

        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);

//        Object result = genericService.$invoke("sayHi", new String[]{"java.lang.String"}, new Object[]{"world"});
//        Map<String, Object> allRequestParams = new HashMap();
//        allRequestParams.put("name", "小傅哥");
//        allRequestParams.put("uid", "10001");
//        Object result = genericService.$invoke("insert", new String[]{"java.lang.Object"}, new Object[]{allRequestParams});

        String[] parameterTypes = new String[]{"java.lang.String", "cn.bugstack.gateway.rpc.dto.XReq"};

        Map<String, Object> params01 = new HashMap<>();
//        params.put("class", "cn.bugstack.gateway.rpc.dto.XReq");
        params01.put("str", "10001");

        Map<String, Object> params02 = new HashMap<>();
//        params.put("str", "10001");
        params02.put("uid", "10001");
        params02.put("name", "小傅哥");

//        Object user = genericService.$invoke("sayHi", new String[]{"java.lang.String"}, params.values().toArray());
//        Object user = genericService.$invoke("insert", new String[]{"cn.bugstack.gateway.rpc.dto.XReq"}, new Object[]{params});
        Object user = genericService.$invoke("test", new String[]{"java.lang.String", "cn.bugstack.gateway.rpc.dto.XReq"}, new Object[]{params01.values().toArray()[0], params02});


        System.out.println(user);
    }

}
