package cn.bugstack.gateway.executor;

import cn.bugstack.gateway.executor.result.GatewayResult;
import cn.bugstack.gateway.mapping.HttpStatement;

import java.util.Map;

public interface Executor {
    GatewayResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;
}
