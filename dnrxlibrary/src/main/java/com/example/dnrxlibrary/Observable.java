package com.example.dnrxlibrary;

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

    /**
     * @param observer 观察者
     */
    //<editor-fold desc="订阅">
    public void subscribe(Observer<? super T> observer){
        observableOnSubscribe.onSubscribe(observer);
    }
    //</editor-fold>

}
