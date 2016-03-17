package com.thunder.ActiveRecord;

/**
 * Created by icepoint1999 on 3/16/16.
 */
public class WhereParams {
    String key;
    String value;
    String rs;

    public WhereParams() {

    }

    public WhereParams(String key, String value, String rs) {

        this.key = key;
        this.value = value;
        this.rs = rs;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }
}
