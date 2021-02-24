package http.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 模拟Servlet工作
 * 缺点 单线程
 * 可定义抽象类AbstractServlet 实现Servlet接口中通用方法
 */
public class Catalina {
    
    static {
        try {
            Container.WEB_CONFIG.load(Catalina.class.getClassLoader().getResourceAsStream("web.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Container.SERVLET_CONTAINER.put("/index", new IndexServlet());
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket accept = server.accept();
            InputStream in = accept.getInputStream();
            StringBuilder sb = new StringBuilder();
            in = accept.getInputStream();
            int len;
            byte[] buf = new byte[512];
            while ((len = in.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
                if (len < buf.length) {
                    accept.shutdownInput();
                }
            }
            // 构建一个请求对象
            Request request = Request.buildRequest(sb.toString());
            request.setRemoteHost(accept.getInetAddress().getHostAddress());
            String url = request.getUrl();
            if ("/favicon.ico".equals(url)){
                continue;
            }
            Servlet servlet = Container.SERVLET_CONTAINER.get(url);
            //如果请求的url在容器中不存在
            if (servlet == null) {
                String fullClassName = Container.WEB_CONFIG.getProperty(url);
                //在配置文件中存在  添加到容器 第二次访问可复用
                if (!"".equals(fullClassName) && fullClassName != null) {
                    servlet = (Servlet) Class.forName(fullClassName).getConstructor().newInstance();
                    Container.SERVLET_CONTAINER.put(url, servlet);
                } else {
                    servlet = Container.SERVLET_CONTAINER.get("/index");
                }
            }
            //构建响应对象
            Response response = new Response();
            response.setOs(accept.getOutputStream());
            
            servlet.service(request, response);
        }
    }
}
