package com.thunder.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public class PathUtil {

    public static final String VAR_REGEXP = ":(\\w+)";
    public static final String VAR_REPLACE = "([^#/?]+)";

    private static final String SLASH = "/";

    public static  String getRelativePath(HttpServletRequest request){

            String path = request.getRequestURI();
            String contextPath = request.getContextPath();
            path = path.substring(contextPath.length());

            if (path.length() > 0) {

                path= path.substring(1);
            }
            if(!path.startsWith(SLASH)){

                path=SLASH+path;
            }

        try {
            path = URLDecoder.decode(path,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return path;

    }
//     在所有路由前+/
    public static String fixPath(String path) {
        if (path == null) {
            return "/";
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    public static String cleanPath(String path) {
        if (path == null) {
            return null;
        }
        return path.replaceAll("[/]+", "/");
    }

    //返回标准路径
    private  static String parsePath(String path) {
        path = PathUtil.fixPath(path);

        URI uri = null;
        try {
            uri = new URI(path);

            return uri.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
//    比较路径是否一致

    public static boolean matchesPath(String routePath, String pathToMatch) {
        routePath = routePath.replaceAll(PathUtil.VAR_REGEXP, PathUtil.VAR_REPLACE);
        //忽略大小写进行匹配
        return pathToMatch.matches("(?i)" + routePath);
    }


    public static void main(String args[]){

       System.out.print( parsePath("/aaa/卧槽"));
    }

}
