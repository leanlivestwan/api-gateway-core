package cn.bugstack.gateway.datasource.connection;

import cn.bugstack.gateway.datasource.Connection;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;


public class HTTPConnection implements Connection {

    private final HttpClient httpClient;
    private PostMethod postMethod;

    public HTTPConnection(String uri) {
        httpClient = new HttpClient();
        postMethod = new PostMethod(uri);
        postMethod.addRequestHeader("accept", "*/*");
        postMethod.addRequestHeader("connection", "Keep-Alive");
        postMethod.addRequestHeader("Content-Type", "application/json;charset=GBK");
        postMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
    }

    @Override
    public Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args) {
        String res = "";
        try {
            // postMethod.addParameter();
            int code = httpClient.executeMethod(postMethod);
            if (code == 200) {
                res = postMethod.getResponseBodyAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
