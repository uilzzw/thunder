package com.thunder.resources;

/**
 * Created by icepoint1999 on 3/4/16.
 */
public class Resource {

    String name ;

    Object controller;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Resource(String name, Object controller) {
        this.name = name;
        this.controller = controller;
    }
}
