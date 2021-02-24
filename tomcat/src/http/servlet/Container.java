package http.servlet;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Container {
    public final static Properties WEB_CONFIG = new Properties();
    public final static Map<String,Servlet> SERVLET_CONTAINER = new HashMap(8);
}
