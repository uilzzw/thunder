package com.thunder.ActiveRecord;

import org.sql2o.Sql2o;

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

}
