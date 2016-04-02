package com.thunder.render;

import java.io.Writer;
import java.util.Map;

/**
 * Created by icepoint1999 on 3/1/16.
 */
public interface Render {

    public void render(String view ,Writer writer);

    public void render(Map<String,Object> map,Writer writer);

    public void render(Object object , Writer writer);
}
