package cn.bugstack.gateway.session;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.bind.MapperRegistry;
import cn.bugstack.gateway.mapping.HttpStatement;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private final MapperRegistry mapperRegistry = new MapperRegistry(this);
    private final Map<String, HttpStatement> httpStatements = new HashMap<>();
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();
    public Configuration() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-gateway-test");
        application.setQosEnable(false);
        RegistryConfig registry = new RegistryConfig();
        registry.setTimeout(50000);
        registry.setAddress("zookeeper://120.78.190.233:2181");
        registry.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("cn.bugstack.gateway.rpc.IActivityBooth");
        reference.setVersion("1.0.0");
        reference.setGeneric("true");

        applicationConfigMap.put("api-gateway-test", application);
        registryConfigMap.put("api-gateway-test", registry);
        referenceConfigMap.put("cn.bugstack.gateway.rpc.IActivityBooth", reference);
    }
    public ApplicationConfig getApplicationConfig(String applicationName) {
        return applicationConfigMap.get(applicationName);
    }

    public RegistryConfig getRegistryConfig(String applicationName) {
        return registryConfigMap.get(applicationName);
    }
    public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName) {
        return referenceConfigMap.get(interfaceName);
    }

    public HttpStatement getHttpStatement(String uri) {
        return httpStatements.get(uri);
    }


    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        return mapperRegistry.getMapper(uri, gatewaySession);
    }

    public void addMapper(HttpStatement httpStatement) {
        mapperRegistry.addMapper(httpStatement);
    }

    public void addHttpStatement(HttpStatement httpStatement) {
        httpStatements.put(httpStatement.getUri(), httpStatement);
    }
}
