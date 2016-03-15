package com.thunder.ActiveRecord;

import org.sql2o.Sql2o;
import test.model.User;

import java.util.List;

/**
 * Created by icepoint1999 on 3/14/16.
 */
public class DB {

static Sql2o sql2o;


public static void open(String driver,String url ,String username,String password){

    try {
        Class.forName(driver);
        sql2o = new Sql2o(url,username,password);

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
    public static void main(String args[]){
        DB.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/zhou", "root", "root");
        List<User> users = Model.where("username","xxx").where("id","26").where("password","223").list(User.class);
        System.out.print(users.get(0).getUsername());
    }

}
