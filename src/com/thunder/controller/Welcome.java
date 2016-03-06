package com.thunder.controller;

import com.thunder.User;
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

    public void create(Request request ,Response response){


        User user = (User) request.getModel(User.class);
        System.out.print(user.getName()+ user.getPass());
    }

    public void fresh(Request request ,Response response){

        System.out.print("create success");
    }

    public void update(Request request ,Response response){

        System.out.print("create success");
    }

    public void delete(Request request ,Response response){

        System.out.print("create success");
    }

    public void edit(Request request ,Response response){

        System.out.print("create success");
    }
}
