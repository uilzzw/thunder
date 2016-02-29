package com.thunder.util;



import sun.plugin.javascript.ReflectUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public class MethodUtil {

    // 执行方法
    public static Object doMethod(Object object ,Method method ,Request request,Response response ){
        int length = method.getParameterTypes().length;
        method.setAccessible(true);
        if( length > 0 ){
            Object[] args = getArgs(request,response,method.getParameterTypes());
            return invokeMethod(object,method,args);
        }else{
            return invokeMethod(object,method);

        }

    }
    //获取方法内的参数

    public static Object[] getArgs(Request request,Response response, Class<?>[] params){

        int length = params.length;
        Object[] args = new Object[length] ;


        for(int i=0 ; i< length ;i++){

            Class<?> paramsTypeClass = params[i];
                if(paramsTypeClass.getName().equals(request.getClass().getName())){

                    args[i] = request;
                }else if(!paramsTypeClass.getName().equals(request.getClass().getName())&&!paramsTypeClass.getName().equals(response.getClass().getName())) {
                        System.out.print("you are sb!");
                }else if(paramsTypeClass.getName().equals(response.getClass().getName())){

                    args[i] =response;

                }

        }
        return args;

    }

    //Object... 代表可以传递不同类型和数量
    public static Object invokeMethod(Object object ,Method method ,Object... args){

        try {
            //         获取参数所在的类类型
            Class<?>[] types = method.getParameterTypes();

            int argcount = args == null ? 0 :args.length ;

            // 2 in 2 xxx 报错 参数个数不对
            makeRuntimeExceptionWhen(argcount!=types.length,"%s in  %s",method.getName(),object);

            //转换参数类型
            for (int i =0 ;i <argcount;i++) {

                args[i] = cast(args[i],types[i]);
            }
            return method.invoke(object,args);
        } catch (Exception e) {
           makeRuntime(e);
        }
    return null;
    }

    //类型转换
    public static <T> T cast(Object value ,Class<T> type){

        if (value != null && !type.isAssignableFrom(value.getClass())) {

            //如果类型是int 或者integer类型  则把对象转换成int类型 以下雷同
            if (is(type, int.class, Integer.class)) {
                value = Integer.parseInt(String.valueOf(value));
            } else if (is(type, long.class, Long.class)) {
                value = Long.parseLong(String.valueOf(value));
            } else if (is(type, float.class, Float.class)) {
                value = Float.parseFloat(String.valueOf(value));
            } else if (is(type, double.class, Double.class)) {
                value = Double.parseDouble(String.valueOf(value));
            } else if (is(type, boolean.class, Boolean.class)) {
                value = Boolean.parseBoolean(String.valueOf(value));
            } else if (is(type, String.class)) {
                value = String.valueOf(value);
            }
        }
//        返回 value的范型
        return (T) value;


    }

//   对象是否其中一个
    public static boolean is(Object obj, Object... mybe) {
        if (obj != null && mybe != null) {
            for (Object mb : mybe)
                if (obj.equals(mb))
                    return true;
        }
        return false;
    }

    //跳出异常
    public static void makeRuntimeExceptionWhen(boolean flag , String message ,Object... args){

            if(flag){

                message = String.format(message ,args);
                RuntimeException e= new RuntimeException(message);
            }

    }

    public static void makeRuntime(Throwable cause) {
        RuntimeException e = new RuntimeException(cause);
        throw correctStackTrace(e);
    }

    /** 移除 Lang层堆栈信息 */
    private static RuntimeException correctStackTrace(RuntimeException e) {
        StackTraceElement[] s = e.getStackTrace();
        if(null != s && s.length > 0){

            e.setStackTrace(Arrays.copyOfRange(s, 1, s.length));
        }
        return e;
    }


}
