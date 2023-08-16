package cn.bugstack.gateway.session.defaults;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.executor.Executor;
import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.GatewaySession;

import java.util.Map;

public class DefaultGatewaySession implements GatewaySession {
    private Configuration configuration;
    private String uri;
    private Executor executor;

    public DefaultGatewaySession(Configuration configuration, String uri, Executor executor) {
        this.configuration = configuration;
        this.uri = uri;
        this.executor = executor;
    }

    @Override
    public Object get(String methodName, Map<String, Object> params) {
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        try {
            return executor.exec(httpStatement, params);
        } catch (Exception e) {
            throw new RuntimeException("Error exec get. Cause: " + e);
        }
    }


    @Override
    public Object post(String methodName, Map<String, Object> params) {
        return get(methodName, params);
    }

    @Override
    public IGenericReference getMapper() {
        return configuration.getMapper(uri, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
