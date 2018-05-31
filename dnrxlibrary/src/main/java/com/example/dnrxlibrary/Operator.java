package com.example.dnrxlibrary;

/**
 * @author : Administrator
 * @time : 13:29
 * @for : 观察者转化器
 *
 * 将泛型为T的观察者  转化成  泛型为R的观察者
 *
 */
public interface Operator<T,R> extends Function<Observer<? super T>,Observer<? super R>> {
}
