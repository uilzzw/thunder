package com.thunder.web;

import com.thunder.core.Lightning;
import com.thunder.core.Thunder;
import com.thunder.route.Route;
import com.thunder.route.RouteMatcher;
import com.thunder.route.Routes;
import com.thunder.util.MethodUtil;
import com.thunder.util.PathUtil;
import com.thunder.util.Request;
import com.thunder.util.Response;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public class ThunderDispatcher extends HttpServlet {

    private Lightning lightning;

    private Thunder thunder;

    private ServletContext servletContext;

    private RouteMatcher routeMatcher = new RouteMatcher(new ArrayList<Route>());


    public ThunderDispatcher() {
    }
    public ThunderDispatcher(Lightning lightning) {

        this.lightning = lightning;
        thunder.init();
    }

    public void init(ServletConfig servletConfig){

            servletContext = servletConfig.getServletContext();
            Thunder zeus  = Thunder.zeus();
            if(!zeus.isInit){

                String className =  servletConfig.getInitParameter("lighting");
                Lightning lightning = this.getLighting(className);
                lightning.init(zeus);
                Routes routes =zeus.getRoutes();

                if(null != routes){
                    routeMatcher.setRouteList(routes.getRoutelist());
                }
                zeus.setIsInit(true);

            }

    }

    public void service(HttpServletRequest httpServletRequest ,HttpServletResponse httpServletResponse){
        System.out.print("in this function");
        //请求的uri
        String uri = PathUtil.getRelativePath(httpServletRequest);

        Route route =routeMatcher.findRoute(uri);



        if (route != null) {
            // 实际执行方法
            handle(httpServletRequest,httpServletResponse, route);

        }
    }



    public   void  handle(HttpServletRequest httpServletRequest ,HttpServletResponse httpServletResponse,Route route){


// 初始化上下文
        Request request = new Request(httpServletRequest);
        Response response = new Response(httpServletResponse);
        ThunderContext.initContext(servletContext, request, response);

        Object controller = route.getController();
        // 要执行的路由方法
        Method actionMethod = route.getMethod();
        // 执行route方法
        MethodUtil.doMethod(controller, actionMethod, request, response);



    }

    private  Lightning  getLighting(String classname){

    if(null!=classname){

        try {
            Class<?> clazz = Class.forName(classname);
            Lightning lightning = (Lightning) clazz.newInstance();
            return lightning;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
        throw new RuntimeException("init lightning class error!");
    }




}
