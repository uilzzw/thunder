package com.thunder.ActiveRecord;

import com.thunder.util.ObjectUtil;
import com.thunder.util.Util;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

/**
 * Created by icepoint1999 on 3/17/16.
 */
public class CustomSql {
    String select = "select * from #table# ";
    List<WhereParams> where ;
    String orderBy;
    boolean is_desc =false;
    String sql ;
    Object update;

    public String getSql(){
        String head = "";
        if(null!=update){
            Map<String,Object> map = ObjectUtil.getFiledValue(this.update);
            Connection connection = DB.sql2o.beginTransaction();
            String tablename = Util.getclassName(this.update.getClass()).toLowerCase();
            String sql = "update "+tablename + " set " ;
            String params ="";
            int i=0;
            for(String key : map.keySet()){
                if(null != map.get(key)&&key!="id"){
                    params += key + "=" + "'"+map.get(key)+"'";
                }
                i++;
                if(i < map.size()-1 &&key!="id"){
                    params += ",";
                }
            }
            sql += params ;
            head = sql;
        }else{
            head = this.select;

        }
        if(null == this.sql){
            if(null!=this.where){
                String params =" where ";
                int i =0 ;
                for (WhereParams whereParams: this.where){
                    params += whereParams.getKey() + whereParams.getRs() +"'"+ whereParams.getValue() +"'";
                    if(i<this.where.size()-1){
                        params += " and ";
                    }
                    i++;
                }
                this.sql = head + params + this.getOrderBy();
            }else{
                String params = "";
                this.sql = head  + params + this.getOrderBy();
            }
        }
        return this.sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }


    public List<WhereParams> getWhere() {
        return where;
    }

    public void setWhere(List<WhereParams> where) {
        this.where = where;
    }

    public String getOrderBy() {
        return null==orderBy ? "" :" order by " + orderBy;
    }

    public Object getUpdate() {
        return update;
    }

    public void setUpdate(Object update) {
        this.update = update;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean is_desc() {
        return is_desc;
    }

    public void setIs_desc(boolean is_desc) {
        this.is_desc = is_desc;
    }
}
