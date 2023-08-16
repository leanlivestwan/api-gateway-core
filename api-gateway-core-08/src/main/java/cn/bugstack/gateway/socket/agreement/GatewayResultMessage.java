package cn.bugstack.gateway.socket.agreement;

/**
 * @author 小傅哥，微信：fustack
 * @description 网关结果封装
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class GatewayResultMessage {

    private String code;
    private String info;
    private Object data;

    protected GatewayResultMessage(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static GatewayResultMessage buildSuccess(Object data) {
        return new GatewayResultMessage(AgreementConstants.ResponseCode._200.getCode(), AgreementConstants.ResponseCode._200.getInfo(), data);
    }

    public static GatewayResultMessage buildError(String code, String info) {
        return new GatewayResultMessage(code, info, null);
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Object getData() {
        return data;
    }

}
