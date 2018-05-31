package com.example.rx.rx;

/**
 * @author : Administrator
 * @time : 17:37
 * @for : 类型转换器  将T类型数据转换成R类型数据
 */
public interface Function<T, R> {
    R apply(T t);
}
