package com.thunder.core;

import com.thunder.render.JspRender;
import com.thunder.render.Render;
import com.thunder.route.Routes;
import com.thunder.util.ConfigLoader;
import com.thunder.util.Request;
import com.thunder.util.Response;

import java.lang.reflect.Method;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public final class Thunder {

//    存放所有路由
    private Routes routes;

//    配置加载

    private ConfigLoader configLoader;

    //加载渲染器

    private Render render;


    public boolean isInit = false;

    public void init(){

            if(this.isInit==false){
                this.isInit=true;
            }
    }

    private Thunder(){
        routes = new Routes();
        configLoader = new ConfigLoader();
        render = new JspRender();
    }

    public void setIsInit(boolean isInit) {
        this.isInit = isInit;
    }

    //单例模式 单例Thunder类 全局只有一个对象

    private static class ThunderSingle{

        private static Thunder ZEUS = new Thunder();
    }

    public static Thunder zeus(){

            return ThunderSingle.ZEUS;

    }

    public Thunder loadConfig(String conf){

        configLoader.load(conf);
        return this;
    }

    public Thunder setConf(String name, String value){
        configLoader.setConf(name, value);
        return this;
    }

    public String getConf(String name){
        return configLoader.getConf(name);
    }

    public Thunder addRoute(String path ,String methodName ,Object controller){

        try {
            Method method =controller.getClass().getMethod(methodName, Request.class , Response.class);

            this.routes.addRoute(path,method,controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;


    }

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }
    public Thunder addRoutes(Routes routes){
        this.routes.addRoute(routes.getRoutelist());
        return this;
    }

    public Render getRender() {
        return render;
    }

    public void setRender(Render render) {
        this.render = render;
    }
}
