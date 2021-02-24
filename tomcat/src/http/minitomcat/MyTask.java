package http.minitomcat;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyTask implements Runnable{
    private Socket accept;
    
    public MyTask(Socket accept){
        this.accept = accept;
    }
    
    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        
        try {
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
            // 拿到输出流
            out = accept.getOutputStream();
            // 构建一个响应
            Response response = new Response();
            response.setData(HttpUtils.getPage(request.getUrl()));
            response.addHeader("a","b");
            response.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (accept != null){
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
