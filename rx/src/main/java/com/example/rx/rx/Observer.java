package com.example.rx.rx;

/**
 * @author : Administrator
 * @time : 17:25
 * @for : 观察者
 */
public abstract class Observer<T> {
    public abstract void onNext(T t);
}
