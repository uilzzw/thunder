package com.thunder.resources;

import com.thunder.util.PathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icepoint1999 on 3/4/16.
 */
public class ResourceMatcher {


    private List<Resource> resourceList;

    public   Resource findResource(String name){

        List<Resource> matchResource = new ArrayList<Resource>();

            for(Resource resource : this.resourceList){

                if(name.equals(resource.getName())){

                    matchResource.add(resource);

                }

            }

        return  matchResource.size() >0 ? matchResource.get(0) : null;

    }

    public ResourceMatcher(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
