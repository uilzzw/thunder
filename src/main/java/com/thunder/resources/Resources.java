package com.thunder.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icepoint1999 on 3/4/16.
 */
public class Resources {

    private List<Resource>  resourcesList = new ArrayList<Resource>();


    public void addResource(List<Resource> resourcesList){

        resourcesList.addAll(resourcesList);

    }

    public void addResource(String name,Object controller){

        Resource resource = new Resource(name,controller);

        resourcesList.add(resource);
    }

    public void addResource(Resource resource){

        resourcesList.add(resource);

    }

    public List<Resource> getResourcesList() {
        return resourcesList;
    }

    public void setResourcesList(List<Resource> resourcesList) {
        this.resourcesList = resourcesList;
    }
}
