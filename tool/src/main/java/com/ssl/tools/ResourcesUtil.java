package com.ssl.tools;

import java.io.File;
import java.net.URL;

/**
 * 描述：获取项目中resources路径
 *
 * @author ssl
 * @create 2018/08/23 11:13
 */
public class ResourcesUtil {
    public static String getClasspath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if (path.startsWith("file:")) {
            path = path.substring(5);
        }
        return path;
    }

    public static String getClasspathFile(String path) {
        String file = "";
        Thread thread = Thread.currentThread();
        ClassLoader contextClassLoader = thread.getContextClassLoader();
        URL resource = contextClassLoader.getResource(path);
         file = resource.getFile();
//        file = Thread.currentThread().getContextClassLoader().getResource(path).getFile();
        return file;
    }

    public static void main(String[] args) {
        System.out.println(getClasspath());
        System.out.println(getClasspathFile("yc1.pdf"));
    }
}
