package cn.bugstack.gateway.bind;

import cn.bugstack.gateway.executor.result.SessionResult;

import java.util.Map;

public interface IGenericReference {
    SessionResult $invoke(Map<String, Object> params);
}
