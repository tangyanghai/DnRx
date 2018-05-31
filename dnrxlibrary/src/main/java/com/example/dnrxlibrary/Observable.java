package com.example.dnrxlibrary;

import android.util.Log;

/**
 * @author : Administrator
 * @time : 8:41
 * @for : 被观察者
 * 发送时间
 */
public class Observable<T> {
    ObservableOnSubscribe<T> observableOnSubscribe;

    /**
     * 私有化构造函数
     * 所有的创建都通过创建操作符来完成
     */
    private Observable(ObservableOnSubscribe<T> observableOnSubscribe) {
        this.observableOnSubscribe = observableOnSubscribe;
    }


    //<editor-fold desc="创建操作符">
    public static <T> Observable<T> create(ObservableOnSubscribe<T> observableOnSubscribe) {
       return new Observable<T>(observableOnSubscribe);
    }
    //</editor-fold>

    //<editor-fold desc="订阅">
    /**
     * 订阅
     * @param observer 观察者
     */
    public void subscribe(Observer<? super T> observer){
        Log.e("RXJava"," 订阅观察者");
        observableOnSubscribe.onSubscribe(observer);
    }
    //</editor-fold>

    /**
     * 转换符 map
     * 将事件T 转换成事件 R
     * 创建一个被观察者,返回
     *
     * 要持有其上一个被观察者 和 function
     */
    public <R> Observable<R> map(Function<? super T,? extends R> function){
        return new Observable(new OnSubscribeLift(observableOnSubscribe,function));
    }

}
