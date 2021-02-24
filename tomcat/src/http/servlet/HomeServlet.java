package http.servlet;

public class HomeServlet implements Servlet {
    public HomeServlet() {
        init();
    }
    
    @Override
    public void init() {
        System.out.println("初始化home 第一次");
    }
    
    @Override
    public void service(Request request, Response response) {
        String remoteHost = request.getRemoteHost();
        System.out.println(remoteHost+"coming");
        response.write("进入HOME");
    }
    
    @Override
    public void destory() {
    
    }
}
