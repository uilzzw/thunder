package com.thunder.controller;

import com.thunder.util.Request;
import com.thunder.util.Response;

/**
 * Created by icepoint1999 on 3/4/16.
 */
public class Welcome {

    public void index(Request request, Response response){

        System.out.print("index success");

    }

    public void show(Request request ,Response response){

        System.out.println("show success");
    }
}
