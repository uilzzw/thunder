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
    private CustomSql customSql;

    public CustomSql getCustomSql() {
        return customSql;
    }

    public void setCustomSql(CustomSql customSql) {
        this.customSql = customSql;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public <T> List<T> list(Class<T> type) {
        List<T> result = null;
        Query query = null;
        String sql = buildSql(this.customSql);
        sql = sql.replaceAll("#table#", Util.getclassName(type).toLowerCase());
        System.out.println(sql);
        query = connection.createQuery(sql);
        result = query.executeAndFetch(type);
        this.connection.close();
        return result;
    }

    public <T> T one(Class<T> type) {
        return list(type).get(0);
    }


    public static String buildSql(CustomSql customSql) {
        return customSql.getSql();
    }

    public ActiveRecord() {

    }

    public ActiveRecord wherelt(String key, String value) {
        WhereParams whereParams = new WhereParams(key, value, "<");
        if (null != this.customSql.where) {
            this.customSql.where.add(whereParams);
        } else {
            this.customSql.where = new ArrayList<WhereParams>();
            this.customSql.where.add(whereParams);
        }
        ;
        return this;
    }

    public ActiveRecord wheregt(String key, String value) {
        WhereParams whereParams = new WhereParams(key, value, ">");
        if (null != this.customSql.where) {
            this.customSql.where.add(whereParams);
        } else {
            this.customSql.where = new ArrayList<WhereParams>();
            this.customSql.where.add(whereParams);
        }
        ;
        return this;
    }

    public ActiveRecord where(String key, String value) {
        WhereParams whereParams = new WhereParams(key, value, "=");
        if (null != this.customSql.where) {
            this.customSql.where.add(whereParams);
        } else {
            this.customSql.where = new ArrayList<WhereParams>();
            this.customSql.where.add(whereParams);
        }
        ;
        return this;
    }

    public ActiveRecord orderBy(String key) {
        this.customSql.setOrderBy(key);
        return this;
    }


    public void update(Object o) {
        this.customSql.setUpdate(o);
        System.out.print(buildSql(this.customSql));
        connection.createQuery(buildSql(this.customSql)).executeUpdate();
    }

  


}

