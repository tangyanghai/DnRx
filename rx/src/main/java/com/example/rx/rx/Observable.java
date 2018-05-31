package com.example.rx.rx;

import com.example.rx.MyLog;

/**
 * @author : Administrator
 * @time : 17:22
 * @for :   被观察者
 */
public class Observable<T> {

    //要持有一个发射器
    Subscribe<T> subscribe;

    private Observable(Subscribe<T> subscribe) {
        MyLog.log("创建了一个观察者"+this.toString());
        this.subscribe = subscribe;
    }

    /*创建符*/
    public static <T> Observable<T> create(Subscribe<T> subscribe){
        return new Observable<T>(subscribe);
    }

    /*订阅*/
    public void subscribe(Observer<? super T> observer){
        subscribe.onSubscribe(observer);
    }


    /**
     * 转换操作符
     * 将泛型<T>的被观察者  转换 成 泛型<R>的被观察者
     * @param <R>
     * @return
     */
    public <R> Observable<R> map(Function<? super T,? extends R> function){
        //要返回一个被观察者
        //就给构造函数一个发射器--map专用的发射器
        return new Observable<R>(new SubscribeMap<T,R>(subscribe,function));
    }


}
