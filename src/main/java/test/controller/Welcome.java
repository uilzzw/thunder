package test.controller;

import com.thunder.util.Request;
import com.thunder.util.Response;
import test.model.User;

/**
 * Created by icepoint1999 on 3/6/16.
 */
public class Welcome {

    public void index(Request request ,Response response){

        System.out.print("success");
        response.render("hello");

    }

    public  void show(Request request ,Response response){

        System.out.print("show");
    }

    public void create(Request request ,Response response){

         User user = (User)request.getModel(User.class);

         System.out.print(user.getUsername());

    }

    public void edit(Request request,Response response){

        System.out.print(request.resourceId());

    }

}
