package com.thunder.helper;

import com.thunder.core.Thunder;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public class ConfigHelper {

    public static String getAppBasePackage(){
        Thunder thunder = Thunder.zeus();
        return thunder.getAppBasePackage();
    }
}
