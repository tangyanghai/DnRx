package com.example.dnrxlibrary;

/**
 * @author : Administrator
 * @time : 8:46
 * @for : 观察者
 */
public abstract class Observer<T> {
    public abstract void onNext(T t);
    public abstract void onCompelet();
    public abstract void onErroe();
}
