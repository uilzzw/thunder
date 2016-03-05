package com.thunder.controller;

import com.thunder.util.Request;
import com.thunder.util.Response;

/**
 * Created by icepoint1999 on 3/4/16.
 */
public class testController {


    public static void show(Request request ,Response response){

        System.out.print(request.resourceId());
        response.render("hello");

    }
    public  static  void index(Request request ,Response response){

        System.out.print(request.resourceId());
        response.render("hello");

    }
}
