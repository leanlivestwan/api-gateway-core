package cn.bugstack.gateway.socket.agreement;

import cn.bugstack.gateway.mapping.HttpStatement;
import io.netty.util.AttributeKey;


public class AgreementConstants {

    public static final AttributeKey<HttpStatement> HTTP_STATEMENT = AttributeKey.valueOf("HttpStatement");

    public enum ResponseCode {
        // 访问成功
        _200("200","访问成功"),
        // 后端接收数据的数据类型不匹配 ，比如前端传送的数据时string,后端使用的是Integer数据类型接收，此时就会包以上错误
        _400("400","接收数据的数据类型不匹配"),
        // （禁止） 服务器拒绝请求。资源不可用，服务器理解客户的请求，但拒绝处理它。通常由于服务器上文件或目录的权限设置导致，比如IIS或者apache设置了访问权限不当。
        _403("403","服务器拒绝请求"),
        // （未找到） 服务器找不到请求的网页；输入链接有误。第一个4表示客户端出错，第二个 0 表示你把网址打错了，最后的那个4表示 “Not Found”，即找不到网页。
        _404("404","服务器找不到请求的网页，输入链接有误"),
        // （服务器内部错误） 服务器遇到错误，无法完成请求。
        _500("500","服务器遇到错误，无法完成请求"),
        // （错误网关） 服务器作为网关或代理，从上游服务器收到无效响应。如 RPC 提供方宕机。
        _502("502","服务器作为网关或代理，从上游服务器收到无效响应");

        private String code;
        private String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

}
