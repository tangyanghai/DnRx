package com.example.rx;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rx.rx.Function;
import com.example.rx.rx.Observable;
import com.example.rx.rx.Observer;
import com.example.rx.rx.Subscribe;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable
                .create(new Subscribe<String>() {
                    @Override
                    public void onSubscribe(Observer<? super String> observer) {
                        MyLog.log("原始的发射器--发出事件");
                        observer.onNext("-->");
                    }
                })
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(String s) {
                        MyLog.log("map转换器--事件转换1");
                        return null;
                    }
                })
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object s) {
                        MyLog.log("map转换器--事件转换2");
                        return null;
                    }
                })
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object s) {
                        MyLog.log("map转换器--事件转换3");
                        return null;
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onNext(Object s) {
                        MyLog.log("观察者--事件结束" + s);
                    }
                });
    }
}
