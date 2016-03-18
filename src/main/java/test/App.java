package test;

import com.thunder.core.Lightning;
import com.thunder.core.Thunder;
import com.thunder.core.Var;
import test.controller.Welcome;

/**
 * Created by icepoint1999 on 3/6/16.
 */
public class App implements Lightning{

    public void init(Thunder thunder) {

        thunder.addResource("welcome",new Welcome());

        thunder.addRoute("/asd", Var.GET,"index",new Welcome());

//        thunder.before("/asd",Var.GET,(request,response)->{
//           System.out.print("asdasd");
//        });


    }
}
