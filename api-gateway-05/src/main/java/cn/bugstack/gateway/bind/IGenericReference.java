package cn.bugstack.gateway.bind;

import java.util.Map;

public interface IGenericReference {
    String $invoke(Map<String, Object> params);
}
