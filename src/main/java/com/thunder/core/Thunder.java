package com.thunder.core;

import com.thunder.render.JspRender;
import com.thunder.render.Render;
import com.thunder.resources.Resources;
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

//    存放所有restful资源

    private Resources resources;

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
        resources =new Resources();
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

    public Thunder addGetRoute(String path ,String methodName ,Object controller){

        try {
            Method method =controller.getClass().getMethod(methodName, Request.class , Response.class);

            this.routes.addRoute(path,Var.GET, method,controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;


    }

    public Thunder addPostRoute(String path ,String methodName ,Object controller){

        try {
            Method method =controller.getClass().getMethod(methodName, Request.class , Response.class);

            this.routes.addRoute(path,Var.GET, method,controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;


    }

    public Thunder addDeleteRoute(String path ,String methodName ,Object controller){

        try {
            Method method =controller.getClass().getMethod(methodName, Request.class , Response.class);

            this.routes.addRoute(path,Var.GET, method,controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;


    }

    public Thunder addPutRoute(String path ,String methodName ,Object controller){

        try {
            Method method =controller.getClass().getMethod(methodName, Request.class , Response.class);

            this.routes.addRoute(path,Var.GET, method,controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;


    }

    public Thunder addPatchRoute(String path ,String methodName ,Object controller){

        try {
            Method method =controller.getClass().getMethod(methodName, Request.class , Response.class);

            this.routes.addRoute(path,Var.GET, method,controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;


    }

    public Thunder addRoute(String path ,String method, String action ,Object controller){

        try {
            Method methodAction =controller.getClass().getMethod(action, Request.class , Response.class);

            this.routes.addRoute(path,  method, methodAction ,controller);

          }
            catch (NoSuchMethodException e) {
               e.printStackTrace();
        }

         return this;


    }

    public Thunder addResource(String name ,Object controller)  {

        this.resources.addResource(name,controller);

        try {

            Method index =controller.getClass().getMethod("index", Request.class , Response.class);

            Method show =controller.getClass().getMethod("show", Request.class , Response.class);

            Method edit =controller.getClass().getMethod("edit", Request.class , Response.class);

            Method create =controller.getClass().getMethod("create", Request.class , Response.class);

            Method update =controller.getClass().getMethod("update", Request.class , Response.class);

            Method fresh =controller.getClass().getMethod("fresh", Request.class , Response.class);

            Method delete =controller.getClass().getMethod("delete", Request.class , Response.class);

            String index_path = "/"+name;

            String show_path  = "/"+name +"/:id";

            String edit_path  = "/"+name +"/:id/edit";

            String delete_path = "/"+name +"/:id/delete";

            String update_path = "/"+name +"/:id/update";

            String create_path = "/"+name +"/create";

            String fresh_path = "/"+name +"/new";

            this.routes.addRoute(index_path, Var.GET, index, controller);

            this.routes.addRoute(show_path, Var.GET, show, controller);

            this.routes.addRoute(edit_path, Var.GET, edit, controller);

            this.routes.addRoute(fresh_path, Var.GET, fresh, controller);

            this.routes.addRoute(delete_path, Var.DELETE, delete, controller);

            this.routes.addRoute(update_path, Var.PUT, update, controller);

            this.routes.addRoute(create_path, Var.POST, create, controller);

            this.routes.addRoute(update_path, Var.PATCH, update, controller);

<<<<<<< HEAD
        } catch (NoSuchMethodException e) {

=======


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
>>>>>>> 8bbceaef4437c38855ad279b3d28bc89d4200987
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

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
