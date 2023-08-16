package cn.bugstack.gateway.authorization;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class GatewayAuthorizingRealm extends AuthorizingRealm {
    @Override
    public Class<?> getAuthenticationTokenClass() {
        return GatewayAuthorizingToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 暂时不需要做授权处理
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            // 验证解析是否报错
            JwtUtil.decode(((GatewayAuthorizingToken) token).getJwt());
        } catch (Exception e) {
            throw new AuthenticationException("无效令牌");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), this.getName());
    }
}
