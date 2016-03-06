package com.thunder.route;

import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by icepoint1999 on 2/29/16.
 * 存放所有路由
 */
public class Routes {

    private List<Route> routelist = new ArrayList<Route>();

    public Routes(){

    }

    public void addRoute(List<Route> routelist){

        routelist.addAll(routelist);
    }

    public void addRoute(Route route){

        routelist.add(route);
    }

    public void addRoute(String path ,String method,Method action ,Object controller){

        Route route = new Route();
        route.setPath(path);
        route.setController(controller);
        route.setAction(action);
        route.setMethod(method);
        routelist.add(route);


    }
    public void removeRoute(Route route){

        routelist.remove(route);
    }

    public List<Route> getRoutelist() {
        return routelist;
    }


    public void setRoutelist(List<Route> routelist) {
        this.routelist = routelist;
    }



}
