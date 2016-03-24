package com.thunder.helper;

import com.thunder.Annotation.Aspect;
import com.thunder.aop.AspectProxy;
import com.thunder.aop.Proxy;
import com.thunder.aop.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public final class Aophelper {


    static{

        try {
            Map<Class<?>,Set<Class<?>>> proxMap =  createProxyMap();
            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxMap);
            for (Map.Entry<Class<?>,List<Proxy>> targetEntry : targetMap.entrySet()){
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    /**
     * 代理类 目标类 映射关系
     * @return
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap(){
            Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>,Set<Class<?>>>();
        Set<Class<?>> proxyClassSet = ClassHelper.getBeanClassSetBySuper(AspectProxy.class);
         for (Class<?> proxyClass : proxyClassSet){
             if (proxyClass.isAnnotationPresent(Aspect.class)){
                 Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                 Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                 proxyMap.put(proxyClass,targetClassSet);
             }

         }
        return proxyMap;
    }

    /**
     * 获取aspect注解中设置的注解类.并且把这些类放入集合中,最终返回这个集合
     * @param aspect
     * @return
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect){

        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annation = aspect.value();
        if (null != annation && !annation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getAspectClassSetByAnnotation(annation));
        }
        return targetClassSet;
    }

    /**
     * 目标类与代理对象列表之间的映射关系
     * @param proxMap
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxMap) throws IllegalAccessException, InstantiationException {

        Map<Class<?>,List<Proxy>> targetMap =  new HashMap<Class<?>,List<Proxy>>();
        for (Map.Entry<Class<?>,Set<Class<?>>> proxEntry : proxMap.entrySet()){
            Class<?> proxyClass  = proxEntry.getKey();
            Set<Class<?>> targetClassSet  = proxEntry.getValue();

            for (Class<?> c : targetClassSet){
                Proxy proxy = (Proxy) proxyClass.newInstance();
                  if (targetMap.containsKey(c)){
                      targetMap.get(c).add(proxy);
                  }else{

                      List<Proxy> proxyList  = new ArrayList<Proxy>();
                      proxyList.add(proxy);
                      targetMap.put(c,proxyList);
                  }
            }
        }

        return targetMap;
    }
}
