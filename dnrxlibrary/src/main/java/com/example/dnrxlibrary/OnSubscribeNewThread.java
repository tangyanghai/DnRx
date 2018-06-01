package com.example.dnrxlibrary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : Administrator
 * @time : 8:16
 * @for : 事件线程转换符的发射器
 */
public class OnSubscribeNewThread<T> implements ObservableOnSubscribe<T> {

    /**
     * 线程池-->控制线程变换
     */
    private ExecutorService mThreadPool;
    /**
     * 上级的观察者
     */
    Observable<T> parentObservable;

    public OnSubscribeNewThread(Observable<T> parentObservable) {
        this.parentObservable = parentObservable;
        mThreadPool = Executors.newCachedThreadPool();
    }

    @Override
    public void onSubscribe(final Observer<? super T> observer) {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                //将事件丢到新的线程中去
                parentObservable.subscribe(observer);
            }
        });
    }
}
