package com.thunder.ActiveRecord;

import com.thunder.util.ObjectUtil;
import com.thunder.util.Util;
import org.sql2o.Connection;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

    public  static <T> List<T> find_by_sql(String sql){
        List<T> list = (List<T>) DB.sql2o.beginTransaction().createQuery(sql).executeAndFetchTable().asList();
        return list;
    }

    public static ActiveRecord where(String params , String value){
        Connection connection = DB.sql2o.beginTransaction();
        List<WhereParams> list = new ArrayList<WhereParams>();
        WhereParams whereParams = new WhereParams(params,value,"=");
        list.add(whereParams);
        ActiveRecord activeRecord = new ActiveRecord();
        activeRecord.setConnection(connection);
        CustomSql customSql = new CustomSql();
        customSql.setWhere(list);
        activeRecord.setCustomSql(customSql);
        return activeRecord;
    }

    public static ActiveRecord whereGt(String key ,String value ){
        Connection connection = DB.sql2o.beginTransaction();
        List<WhereParams> list = new ArrayList<WhereParams>();
        WhereParams whereParams = new WhereParams(key,value,">");
        list.add(whereParams);
        ActiveRecord activeRecord = new ActiveRecord();
        activeRecord.setConnection(connection);
        CustomSql customSql = new CustomSql();
        customSql.setWhere(list);
        activeRecord.setCustomSql(customSql);
        return activeRecord;
    }

    public static ActiveRecord whereLt(String key ,String value ){
        Connection connection = DB.sql2o.beginTransaction();
        List<WhereParams> list = new ArrayList<WhereParams>();
        WhereParams whereParams = new WhereParams(key,value,"<");
        list.add(whereParams);
        ActiveRecord activeRecord = new ActiveRecord();
        activeRecord.setConnection(connection);
        CustomSql customSql = new CustomSql();
        customSql.setWhere(list);
        activeRecord.setCustomSql(customSql);
        return activeRecord;
    }

    public static ActiveRecord select(String... args){
        Connection connection = DB.sql2o.beginTransaction();
        String[] selects = args;
        ActiveRecord activeRecord = new ActiveRecord();
        String select = "select ";
        int i = 0;
        for(String val :args){
            select += val+" ";
            if(i<args.length-1){
                select+= ", ";
            }
            i++;
        }
        CustomSql customSql = new CustomSql() ;
        customSql.setSelect(select+" from #table# ");
        activeRecord.setCustomSql(customSql);
        activeRecord.setConnection(connection);
        return activeRecord;
    }

    public static void  save(Object object){
        Connection connection = DB.sql2o.beginTransaction();
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuffer key = new StringBuffer();
        StringBuffer value = new StringBuffer();
        String table = Util.getclassName(object.getClass()).toLowerCase();
        int i=0;
         for(Field field :fields){
             field.setAccessible(true);
             key.append(field.getName());
             try {
                 String v = null==field.get(object)?  "" : field.get(object).toString();
                 value.append("'"+v+"'");
             } catch (IllegalAccessException e) {
                 e.printStackTrace();
             }
             i++;
             if(i<fields.length){
                 key.append(",");
                 value.append(",");
             }
         }
        String sql = "insert into "+table + "("+key +") values ("+value +")";
        System.out.println("excute sql ------->"+sql);
        connection.createQuery(sql).executeUpdate().commit();
    }

    public ActiveRecord all(){
        Connection connection = DB.sql2o.beginTransaction();
        String sql = "select * from ";
        ActiveRecord activeRecord = new ActiveRecord();
        CustomSql customSql = new CustomSql();
        customSql.setSql(sql);
        activeRecord.setCustomSql(customSql);
        return activeRecord;
    }

    public  static  <T> List<T> find_all(Class<T> tClass){
        Connection connection = DB.sql2o.beginTransaction();
        String table = Util.getclassName(tClass).toLowerCase();
        String sql = "select * from "+table;
        List<T> list = connection.createQuery(sql).executeAndFetch(tClass);
        return list;
    }

    public static void update(Object object){
        Map<String,Object> map = ObjectUtil.getFiledValue(object);
        Connection connection = DB.sql2o.beginTransaction();
        String tablename = Util.getclassName(object.getClass()).toLowerCase();
        String sql = "update "+tablename + " set " ;
        String params ="";
        int i=0;
        for(String key : map.keySet()){
           if(null != map.get(key)&&key!="id"){
               params += key + "=" + map.get(key);
           }
            i++;
            if(i < map.size()-1 &&key!="id"){

                params += ",";
            }
        }
        sql += params + " where id = " + map.get("id");
        System.out.print(sql);
    }

//    public static ActiveRecord updateBy(Object o){
//        Connection connection = DB.sql2o.beginTransaction();
//        ActiveRecord ac = new ActiveRecord();
//        CustomSql customSql = new CustomSql();
//        customSql.setUpdate(o);
//        ac.setCustomSql(customSql);
//        return ac;
//
//    }



}
