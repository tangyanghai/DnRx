package com.example.rx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Rxjava使用验证
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    Button btn;
    private final int SECOND = 10;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);

        RxView
                .clicks(btn)
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //点击之后  设置时间  设置为不可点击
                        RxView.enabled(btn).accept(false);
                        RxTextView.text(btn).accept("" + SECOND);
                    }
                })
                .observeOn(Schedulers.newThread())
                .flatMap(new Function<Object, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(Object o) throws Exception {
                        //开启倒计时
                        //1秒一次 一共60次  返回次数
                        return io.reactivex.Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                                .take(SECOND);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        //每次倒计时都改变按钮上的数字
                        Long remain = SECOND - aLong - 1;
                        RxTextView.text(btn).accept(remain.toString());
                        return remain;
                    }
                })
                .filter(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        //过滤掉不是0的数字
                        return aLong == 0;
                    }
                })
                //订阅
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        //数字是0了,恢复点击
                        RxView.enabled(btn).accept(true);
                        RxTextView.text(btn).accept("获取验证码");
                    }
                })
        ;

    }
}
