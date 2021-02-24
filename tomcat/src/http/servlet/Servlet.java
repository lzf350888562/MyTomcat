package http.servlet;

public interface Servlet {
    /**
     * 初始化
     */
    void init();
    
    /**
     * 处理请求的服务
     * @param request
     * @param response
     */
    void service(Request request,Response response);
    
    void destory();
}
