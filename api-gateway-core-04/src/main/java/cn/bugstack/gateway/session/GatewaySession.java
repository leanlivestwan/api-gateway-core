package cn.bugstack.gateway.session;

import cn.bugstack.gateway.bind.IGenericReference;

public interface GatewaySession {
    Object get(String methodName, Object parameter);
    IGenericReference getMapper();
    Configuration getConfiguration();
}
