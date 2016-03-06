package com.thunder;

/**
 * Created by icepoint1999 on 3/6/16.
 */
public class User {

    private String name;
    private String pass ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User() {
    }
}
