package com.example.dnrxlibrary;

/**
 * @author : Administrator
 * @time : 8:44
 * @for : 发射器-->将事件从被观察者,发送到观察者
 */
public interface ObservableOnSubscribe<T> extends Action1<Observer<? super T>> {

}
