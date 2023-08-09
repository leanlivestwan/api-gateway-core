package cn.bugstack.gateway.session;

import cn.bugstack.gateway.bind.IGenericReference;

public interface GatewaySession {
    Object get(String uri, Object parameter);
    IGenericReference getMapper(String uri);
    Configuration getConfiguration();
}
