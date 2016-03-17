package com.thunder.ActiveRecord;

import java.util.List;

/**
 * Created by icepoint1999 on 3/17/16.
 */
public class CustomSql {
    String select = "select * ";
    String from =" from #table# ";
    List<WhereParams> where ;
    String orderBy;
    boolean is_desc =false;
    String sql ;

    public String getSql() {
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
                this.sql = this.select + this.from + params + this.getOrderBy();
            }else{
                String params = "";
                this.sql = this.select + this.from + params + this.getOrderBy();
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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
