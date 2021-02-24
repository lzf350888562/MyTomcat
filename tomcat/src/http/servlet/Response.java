package http.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private String protocol = "HTTP/1.1";
    private Integer code = 200;
    private String msg = "OK";
    private String ContentType = "text/html;charset=utf-8";
    private String ContentLength;
    private Map<String,String > headers = new HashMap(){{
        put("content-type",ContentType);
    }};
    private String data;
    
    private OutputStream os;
    
    public Response(){}
    
    public Response(String protocol, Integer code, String msg) {
        this.protocol = protocol;
        this.code = code;
        this.msg = msg;
    }
    
    
    /**
     * 构建响应
     * @return
     */
    public String buildResponse(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getProtocol()).append(" ")
                .append(this.getCode()).append(" ")
                .append(this.getMsg()).append("\r\n");
        for (Map.Entry<String,String> entry : this.getHeaders().entrySet()){
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
        }
        sb.append("\r\n").append(this.getData());
        return sb.toString();
    }
    /**
     * 输出响应
     */
    public void write(String content){
        this.setData(content);
        this.write();
    }
    /**
     * 输出响应
     */
    public void write(){
        try {
            os.write(buildResponse().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    /**
     * 加一个响应头
     * @param key
     * @param value
     */
    public void addHeader(String key,String value){
        this.getHeaders().put(key,value);
    }
    
    public String getProtocol() {
        return protocol;
    }
    
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Map<String, String> getHeaders() {
        return headers;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
        this.setContentLength(data.getBytes().length+"");
    }
    
    public String getContentType() {
        return this.getHeaders().get("content-type");
    }
    
    public void setContentType(String contentType) {
        this.getHeaders().put("content-type",contentType);
    }
    
    public String getContentLength() {
        return  this.getHeaders().get("content-length");
    }
    
    public void setContentLength(String contentLength) {
        this.getHeaders().put("content-length",this.data.getBytes().length + "");
    }
    
    public OutputStream getOs() {
        return os;
    }
    
    public void setOs(OutputStream os) {
        this.os = os;
    }
}
