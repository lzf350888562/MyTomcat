package http.minitomcat;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Http {
    public static void main(String[] args) throws Exception {
        // 创建服务器
        ServerSocket server = new ServerSocket();
        // 绑定端口
        server.bind(new InetSocketAddress(4888));
        System.out.println("server is running! listening on port 4888!");
        // 开始监听，阻塞的方法
        while (true){
            Socket accept = server.accept();
            System.out.println("A person"+accept.getRemoteSocketAddress().toString()+" is coming!");
            
            // 使用线程池，提升性能
            ExecutorService executorService = Executors.newFixedThreadPool(20);
            executorService.submit(new MyTask(accept));
        }
    }
}
