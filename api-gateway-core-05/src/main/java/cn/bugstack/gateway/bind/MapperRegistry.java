package cn.bugstack.gateway.bind;

import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.GatewaySession;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {
    private final Configuration configuration;
    private final Map<String, MapperProxyFactory> knownMappers = new HashMap<>();
    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }


    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        final MapperProxyFactory mapperProxyFactory = knownMappers.get(uri);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Uri " + uri + " is not known to the MapperRegistry.");
        }
        return mapperProxyFactory.newInstance(gatewaySession);
    }

    public void addMapper(HttpStatement httpStatement) {
        String uri = httpStatement.getUri();
        if (hasMapper(uri)) {
            throw new RuntimeException("Uri " + uri + " is already known to the MapperRegistry.");
        }
        knownMappers.put(uri,new MapperProxyFactory(uri));
        // 保存接口映射信息
        configuration.addHttpStatement(httpStatement);
    }
    public <T> boolean hasMapper(String uri) {
        return knownMappers.containsKey(uri);
    }
}
