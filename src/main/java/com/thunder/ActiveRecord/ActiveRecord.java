package com.thunder.ActiveRecord;

import com.thunder.util.Util;
import org.sql2o.Connection;
import org.sql2o.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by icepoint1999 on 3/15/16.
 */
public class ActiveRecord {

    private Connection connection;
    private String customSql;
    private Map<String,String> where_params;
    private Map<String,String> select_params;

    public ActiveRecord(Connection connection, String customSql, Map<String, String> params) {
        this.connection = connection;
        this.customSql = customSql;
        this.where_params = params;
    }

    public ActiveRecord(Connection connection, Map<String, String> params) {
        this.connection = connection;
        this.where_params = params;
    }

    public ActiveRecord(Map<String, String> params) {

        this.where_params = params;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCustomSql() {
        return customSql;
    }

    public void setCustomSql(String customSql) {
        this.customSql = customSql;
    }

    public ActiveRecord(Connection connection, String customSql) {
        this.connection = connection;
        this.customSql = customSql;
    }

    public <T> List<T> list(Class<T> type){
        List<T> result =null;
        Query query = null;
        if(0==this.where_params.size()){
         query   = connection.createQuery(this.getCustomSql());
        }else{
            String sql = "select * from " + Util.getclassName(type).toLowerCase()+buildWhereSql(this.where_params);
            System.out.print(sql);
            query = connection.createQuery(sql);
        }
        result = query.executeAndFetch(type);
        return result;
    }

    public String buildWhereSql(Map<String,String> map){
        String params = " where ";
        int i =0 ;
        for (String key: map.keySet()){
            params += key + "=" +"'"+ map.get(key) +"'";
            if(i<map.keySet().size()-1){
                params += " and ";
            }
            i++;
        }
        return params;
    }

    public ActiveRecord where(String key,String value){

        this.where_params.put(key,value);
        return this;

    }
}
