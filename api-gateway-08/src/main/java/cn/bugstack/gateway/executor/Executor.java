package cn.bugstack.gateway.executor;

import cn.bugstack.gateway.executor.result.SessionResult;
import cn.bugstack.gateway.mapping.HttpStatement;

import java.util.Map;

public interface Executor {
    SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;
}
