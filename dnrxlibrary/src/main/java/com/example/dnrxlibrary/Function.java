package com.example.dnrxlibrary;

/**
 * @author : Administrator
 * @time : 11:35
 * @for : 事件转换器
 */
public interface Function<T,R> {
    R apply(T t);
}
