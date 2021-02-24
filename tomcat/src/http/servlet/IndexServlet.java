package http.servlet;


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
        System.out.println(remoteHost+"coming");
        response.write("<h1>进入首页index</h1>");
    }
    
    @Override
    public void destory() {
    
    }
}
