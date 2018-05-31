package com.example.rx.rx;

/**
 * @author : Administrator
 * @time : 17:38
 * @for : 观察者转换器,将泛型为<R>的观察者,转换成泛型为<T>的观察者
 */
public interface Operator<T,R> extends Function<Observer<? super T>,Observer<? super R>> {
}
