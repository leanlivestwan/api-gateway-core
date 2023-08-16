package cn.bugstack.gateway.session;

public interface GatewaySessionFactory {
    GatewaySession openSession(String uri);
}
