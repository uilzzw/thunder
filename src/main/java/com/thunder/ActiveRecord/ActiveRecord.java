package com.thunder.ActiveRecord;

import com.thunder.util.Util;
import jdk.nashorn.internal.objects.annotations.Where;
import org.sql2o.Connection;
import org.sql2o.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by icepoint1999 on 3/15/16.
 */
public class ActiveRecord {

    private Connection connection;
    private String customSql;
    private List<WhereParams> where_params;
    private String orderBy ;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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
        if(null==this.where_params){
            this.customSql =this.customSql.replaceAll("#table#",Util.getclassName(type).toLowerCase());
            System.out.print(this.customSql);
            query = connection.createQuery(this.customSql);
        }else{
            String sql = buildSql(this.where_params);
            sql = sql.replaceAll("#table#",Util.getclassName(type).toLowerCase());
            System.out.println(sql);
            query = connection.createQuery(sql);
        }
        result = query.executeAndFetch(type);
        this.connection.close();
        return result;
    }
    public <T> T one(Class<T> type){
        return list(type).get(0);
    }
    public static void save(Object o){

    }

    public List<WhereParams> getWhere_params() {
        return where_params;
    }

    public void setWhere_params(List<WhereParams> where_params) {
        this.where_params = where_params;
    }

    public String buildSql(List<WhereParams> list){
        String params =" where ";
        int i =0 ;
        for (WhereParams whereParams: list){
            params += whereParams.getKey() + whereParams.getRs() +"'"+ whereParams.getValue() +"'";
            if(i<list.size()-1){
                params += " and ";
            }
            i++;
        }
        if(null!=this.orderBy){
            String order ="order by "+ this.orderBy;
            this.customSql += params ;
            this.customSql += order;
        }
        else{

            this.customSql += params;
        }

        return this.customSql;
    }

    public ActiveRecord() {
    }

    public ActiveRecord where(String key,String value){
     if(null==this.where_params){
         this.where_params= new ArrayList<WhereParams>();
     }WhereParams whereParams = new WhereParams(key,value,"=");
        this.where_params.add(whereParams);
        return this;
    }

    public ActiveRecord whereLt(String key,String value){
        if(null==this.where_params){
            this.where_params= new ArrayList<WhereParams>();
        }WhereParams whereParams = new WhereParams(key,value,"<");
        this.where_params.add(whereParams);
        return this;
    }

    public ActiveRecord whereGt(String key,String value){
        if(null==this.where_params){
            this.where_params= new ArrayList<WhereParams>();
        }WhereParams whereParams = new WhereParams(key,value,">");
        this.where_params.add(whereParams);
        return this;
    }

    public ActiveRecord orderBy(String orderBy){
        this.orderBy = orderBy;
        return this;
    }



}
