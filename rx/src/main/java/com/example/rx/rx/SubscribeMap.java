package com.example.rx.rx;

/**
 * @author : Administrator
 * @time : 17:41
 * @for : 转换符--map专用的发射器
 *
 *
 */
public class SubscribeMap<T,R> implements Subscribe<R>{

    //上级被观察者的发射器,用来在被订阅的时候发射事件
    Subscribe<T> parentSubscribe;

    //观察者转换器,将下级观察者转换成上级观察者
    Operator<R,T> operator;
    public SubscribeMap(Subscribe<T> parentSubscribe, Function<? super T, ? extends R> function) {
        this.parentSubscribe = parentSubscribe;
        operator = new OperatorMap<>(function);
    }

    /**
     * 订阅
     * @param observer
     */
    @Override
    public void onSubscribe(Observer<? super R> observer) {
        Observer<? super T> apply = operator.apply(observer);
        parentSubscribe.onSubscribe(apply);
    }
}
