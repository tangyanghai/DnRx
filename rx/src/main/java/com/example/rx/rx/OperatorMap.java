package com.example.rx.rx;

/**
 * @author : Administrator
 * @time : 17:50
 * @for : map专用的观察者转换器工具 以及 map的观察者
 */
public class OperatorMap<T, R> implements Operator<R, T> {
    Function<? super T, ? extends R> function;

    public OperatorMap(Function<? super T, ? extends R> function) {
        this.function = function;
    }

    @Override
    public Observer<? super T> apply(Observer<? super R> observer) {
        return new ObserverMap<>(observer, function);
    }

    /**
     * map的观察者
     *
     * @param <T> map观察者应该持有的泛型
     * @param <R> map下一观察者持有的泛型
     */
    class ObserverMap<T, R> extends Observer<T> {
        Function<? super T, ? extends R> transform;
        Observer<? super R> nextObserver;

        public ObserverMap(Observer<? super R> nextObserver, Function<? super T, ? extends R> transform) {
            this.nextObserver = nextObserver;
            this.transform = transform;
        }

        @Override
        public void onNext(T t) {
            R r = transform.apply(t);
            nextObserver.onNext(r);
        }
    }

}
