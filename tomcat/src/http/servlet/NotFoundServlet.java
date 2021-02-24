package http.servlet;

public class NotFoundServlet implements Servlet {
    public NotFoundServlet() {
        init();
    }
    
    @Override
    public void init() {
        System.out.println("初始化 notfound 第一次");
    }
    
    @Override
    public void service(Request request, Response response) {
        String remoteHost = request.getRemoteHost();
        System.out.println(remoteHost+"coming");
        response.write("<h1>NOT FOUND</h1>");
    }
    
    @Override
    public void destory() {
    
    }
}
