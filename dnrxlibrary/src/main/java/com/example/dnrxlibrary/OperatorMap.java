package com.example.dnrxlibrary;

import android.util.Log;

/**
 * @author : Administrator
 * @time : 13:31
 * @for : map转换符对应的观察者转换器 将传进来的观察者,转换成上一个被观察者需要的观察者
 */
public class OperatorMap<T,R> implements Operator<R,T> {


    Function<? super T, ? extends R> function;

    public OperatorMap(Function<? super T, ? extends R> function) {
        this.function = function;
    }

    @Override
    public Observer<? super T> apply(Observer<? super R> observer) {
        return new ObserverMap(observer,function);
    }

    /**
     * map对应的观察者--事件的转换实际上是在这里的onNext做的
     */
    protected class ObserverMap<T,R> extends Observer<T>{

        /**
         * 订阅的观察者
         */
        Observer<? super R> nextObserver;
        /**
         * 事件转换器
         */
        Function<T,R> transform;

        public ObserverMap(Observer<? super R> nextObserver, Function<T,R> transform) {
            Log.e("RXJava","map 的观察者被创建");
            this.nextObserver = nextObserver;
            this.transform = transform;
        }

        @Override
        public void onNext(T t) {
            //这里体现事件传递流程
            Log.e("RXJava","map 的onNext被调用");
            R r = transform.apply(t);
            nextObserver.onNext(r);
        }

        @Override
        public void onCompelet() {

        }

        @Override
        public void onErroe() {

        }
    }

}
