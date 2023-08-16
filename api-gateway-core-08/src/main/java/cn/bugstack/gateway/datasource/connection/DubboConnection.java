package cn.bugstack.gateway.datasource.connection;

import cn.bugstack.gateway.datasource.Connection;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

public class DubboConnection implements Connection {
    private final GenericService genericService;

    public DubboConnection(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ReferenceConfig<GenericService> reference) {
        // 连接远程服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();

        configCenterConfig.setTimeout(50000L);
        bootstrap.configCenter(configCenterConfig);
        bootstrap.application(applicationConfig).registry(registryConfig).reference(reference).start();

        // 获取泛化接口
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        genericService = cache.get(reference);
    }

    @Override
    public Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args) {
        return genericService.$invoke(method, parameterTypes, args);
    }
}
