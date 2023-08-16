package cn.bugstack.gateway.bind;

import cn.bugstack.gateway.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy implements MethodInterceptor {
    private GatewaySession gatewaySession;
    private final String uri;
    public MapperProxy(GatewaySession gatewaySession, String uri)  {
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        MapperMethod linkMethod = new MapperMethod(uri, method, gatewaySession.getConfiguration());
        return linkMethod.execute(gatewaySession, (Map<String, Object>) args[0]);
    }
}
