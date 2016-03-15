package com.thunder.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by icepoint1999 on 3/4/16.
 */
public class Resources {

    private List<Resource>  resourcesList = new ArrayList<Resource>();

    private static final Logger logger = Logger.getLogger(Resources.class.getName());

    public void addResource(List<Resource> resourcesList){

        resourcesList.addAll(resourcesList);

    }

    public void addResource(String name,Object controller){

        Resource resource = new Resource(name,controller);

        resourcesList.add(resource);

        logger.info("Add resource :" + name);
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
