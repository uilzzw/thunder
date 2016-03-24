package com.thunder.aop;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public interface Proxy {

    Object doProxy(ProxyChain proxyChain)  throws Throwable;
}
