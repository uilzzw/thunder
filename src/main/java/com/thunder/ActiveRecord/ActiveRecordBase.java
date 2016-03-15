package com.thunder.ActiveRecord;

import org.sql2o.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by icepoint1999 on 3/14/16.
 */
public  abstract class ActiveRecordBase {

    public static  <T> T find_by_id(int id ,Class<T> tClass){
        String[] tablename =tClass.getName().toLowerCase().split("[.]");
        String table = tablename[tablename.length-1];
        List<T> list = (List<T>) DB.sql2o.beginTransaction().createQuery("select * from " + table+ " where id = " +id).executeAndFetch(tClass);
        return list.get(0);
    }

    public  static ActiveRecord find_by_sql(String sql){
        Connection connection = DB.sql2o.beginTransaction();
        return new ActiveRecord(connection,sql);
    }

    public static ActiveRecord where(String params , String value){
        Connection connection = DB.sql2o.beginTransaction();
        Map map = new HashMap();
        map.put(params,value);

        return new ActiveRecord(connection,map);
    }

//    public static ActiveRecord select(){
//
//        Connection connection = DB.sql2o.beginTransaction();
//
//    }


}
