package http.servlet;

import java.util.Map;

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
        System.out.println(remoteHost + "is coming");
        //获取cookie
        String cookie = request.getHeaders().get("cookie");
        if (cookie == null) {
            System.out.println("请您登录!");
            response.write("<h1>您没有登录(进入not页面登录)</h1>");
        } else {
            String sessionid = cookie.split("=")[1];
            Map<String, Object> session = Container.SESSIONS.get(sessionid);
            //模拟检测是否登录过
            if (session == null) {
                response.write("<h1>需要清空缓存再测试</h1>");
            } else {
                if (session.get("user") == null) {
                    response.write("<h1>有登录记录 但账号不对</h1>");
                } else {
                    response.write("<h1>您已登录" + session.get("user") + "</h1>");
                }
            }
        }
    }
    
    @Override
    public void destory() {
    
    }
}
