package com.thunder.util;



import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public class ClassUtil {

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public  static  Class<?> loadClass(String className,boolean isInit){
        Class<?> c;
        try {
            c = Class.forName(className,isInit,getClassLoader());

        } catch (ClassNotFoundException e) {
            System.out.println("load " + className +"失败");
            throw  new  RuntimeException(e);
        }
        return c;
    }
//    获取指定包名下的所有的类
    public static Set<Class<?>> getClassList(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if (null!=url){
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")){
                        String packagePath = url.getPath().replaceAll("%20"," ");
                        addClass(classSet,packagePath,packageName);
                    }else if (protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if(null != jarURLConnection){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (null != jarFile){
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while(jarEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
    }
    return  classSet;

    }

    private  static  void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return(file.isFile()&& file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for(File file :files){
            String filename = file.getName();
            if (file.isFile()){
                String className = filename.substring(0,filename.lastIndexOf("."));
                if (!packageName.isEmpty()){
                    className = packageName + "."+className;
                }
                doAddClass(classSet,className);
            }else{
                String subPackagePath = filename;
                if (!packagePath.isEmpty()){
                    subPackagePath = packagePath+"/"+subPackagePath;
                }
                String subPackageName = filename;
                if (!packageName.isEmpty()){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName);
            }
        }
    }
    private static void doAddClass(Set<Class<?>> classSet ,String className){
        Class<?> c = loadClass(className,false);
        classSet.add(c);
    }

//    public  static  void main(String args[]){
//
//        System.out.print(getClassList("").size());;
//    }

}
