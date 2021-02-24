package http.servlet;


import java.util.Map;

public class IndexServlet implements Servlet {
    public IndexServlet() {
        init();
    }
    
    @Override
    public void init() {
        System.out.println("初始化index 第一次");
    }
    
    @Override
    public void service(Request request, Response response) {
        String remoteHost = request.getRemoteHost();
        System.out.println(remoteHost+"is coming");
        
    }
    
    @Override
    public void destory() {
    
    }
}
