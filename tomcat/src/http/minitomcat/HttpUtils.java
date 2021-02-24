package http.minitomcat;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HttpUtils {
    // 使用流获得页面的字符串
    public static String getPage(String url){
        StringBuilder sb = new StringBuilder();
        try {
            if ("".equals(url) || "/".equals(url) || url == null){
                url = "index.html";
            }
            // 寻找觉对的父路劲
            String path = HttpUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(0,path.lastIndexOf("/")) + "/pages";
            url = path + url;
            boolean exists = new File(url).exists();
            if (!exists){
                url = path + "/404.html";
            }
            
            InputStream resource = new FileInputStream(url);
            byte[] buf = new byte[1024];
            int len;
            while ((len = resource.read(buf)) != -1){
                sb.append(new String(buf,0,len));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
