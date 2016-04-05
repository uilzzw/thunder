package com.thunder.wrapper;

/**
 * Created by icepoint1999 on 4/5/16.
 */
public interface Restful {

    public void index(Request request , Response response);



    public void show(Request request , Response response);




    public void edit(Request request , Response response);



    public void update(Request request , Response response);



    public void create(Request request , Response response);



    public void fresh(Request request , Response response);



    public void delete(Request request , Response response);

}
