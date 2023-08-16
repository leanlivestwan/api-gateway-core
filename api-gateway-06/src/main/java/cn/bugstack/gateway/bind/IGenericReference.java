package cn.bugstack.gateway.bind;

import java.util.Map;

public interface IGenericReference {
    Object $invoke(Map<String, Object> params);
}
