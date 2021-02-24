package http.servlet;

import java.util.Map;

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
        //获取cookie
        String cookie = request.getHeaders().get("cookie");
        if (cookie==null){
            System.out.println("请您登录!");
            response.write("<h1>请您登录(刷新)</h1>");
        }else {
            String sessionid = cookie.split("=")[1];
            Map<String, Object> session = Container.SESSIONS.get(sessionid);
            //模拟登录成功
            session.put("user", "zhangsan");
            response.write("<h1>登录成功</h1>");
        }
    }
    
    @Override
    public void destory() {
    
    }
}
