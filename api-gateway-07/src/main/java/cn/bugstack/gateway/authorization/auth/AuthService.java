package cn.bugstack.gateway.authorization.auth;

import cn.bugstack.gateway.authorization.GatewayAuthorizingToken;
import cn.bugstack.gateway.authorization.IAuth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class AuthService implements IAuth {
    private Subject subject;
    public AuthService() {
        // 1. 获取 SecurityManager 工厂，此处使用 shiro.ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2. 得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 3. 得到 Subject 及 Token
        // 3. 得到 Subject 及 Token
        this.subject = SecurityUtils.getSubject();
    }

    @Override
    public boolean validate(String id, String token) {
        try {
            // 身份验证
            subject.login(new GatewayAuthorizingToken(id, token));
            // 返回结果
            return subject.isAuthenticated();
        } finally {
            // 退出
            subject.logout();
        }
    }
}
