package http.minitomcat;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String type;
    private String url;
    private String protocol;
    private String contentType;
    
    
    private Map<String,String> headers = new HashMap<>(8);
    
    private Map<String,String> attributes = new HashMap<>(8);
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getProtocol() {
        return protocol;
    }
    
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    public String getContentType() {
        return contentType;
    }
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public Map<String, String> getHeaders() {
        return headers;
    }
    
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
    public Map<String, String> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    
    @Override
    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", protocol='" + protocol + '\'' +
                ", contentType='" + contentType + '\'' +
                ", headers=" + headers +
                ", attributes=" + attributes +
                '}';
    }
    
    // 通过请求的报文字符串构建一个请求对象
    public static Request buildRequest(String requestStr){
        Request request = new Request();
        String[] split = requestStr.split("\r\n\r\n");
        // 请求行 和 请求头
        String[] lineAndHeader = split[0].split("\r\n");
        String[] lines = lineAndHeader[0].split(" ");
        request.setType(lines[0]);
        request.setUrl(lines[1]);
        request.setProtocol(lines[2]);
        
        for (int i = 1; i < lineAndHeader.length; i++) {
            String[] header = lineAndHeader[i].split(": ");
            request.getHeaders().put(header[0].trim().toLowerCase(),header[1].trim());
        }
        
        request.setContentType(request.getHeaders().get("content-type"));
        
        // 处理请求体
        if (split.length == 2){
            // 可以自己使用postman模拟一个post请求进行分割和存储
        }
        
        return request;
    }
}
