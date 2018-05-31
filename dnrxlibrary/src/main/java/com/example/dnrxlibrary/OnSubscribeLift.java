package com.example.dnrxlibrary;

import android.util.Log;

/**
 * @author : Administrator
 * @time : 11:55
 * @for : 转换符的发射器
 *
 * 需要持有
 *
 * 上一个发射器
 * 转换实体
 *
 */
public class OnSubscribeLift<T,R> implements ObservableOnSubscribe<R>{
    /**
     * 上一个被观察者的发射器
     */
    ObservableOnSubscribe<T> parentObservableOnSubscribe;
    Operator<? extends R,? super  T> operator;
    public OnSubscribeLift(ObservableOnSubscribe<T> parentObservableOnSubscribe, Function<? super T, ? extends R> function) {
        Log.e("RXJava","map 的发射器被创建");
        this.parentObservableOnSubscribe = parentObservableOnSubscribe;
        this.operator = new OperatorMap<T,R>(function);
    }

    /**
     * 订阅
     * @param observer 下一个观察者
     */
    @Override
    public void onSubscribe(Observer<? super R> observer) {
        Log.e("RXJava","map 的发射器被触发");

        //1. 将下一个观察者转换成自己观察者
        Observer<? super T> t = operator.apply(observer);
        //2. 上一个发射器订阅自己的观察者
        parentObservableOnSubscribe.onSubscribe(t);
    }
}
