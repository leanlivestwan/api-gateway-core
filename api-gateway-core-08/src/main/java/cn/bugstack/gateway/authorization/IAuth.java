package cn.bugstack.gateway.authorization;

public interface IAuth {
    boolean validate(String id, String token);
}
