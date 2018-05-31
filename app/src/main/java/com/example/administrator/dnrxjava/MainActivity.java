package com.example.administrator.dnrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dnrxlibrary.Function;
import com.example.dnrxlibrary.Observable;
import com.example.dnrxlibrary.ObservableOnSubscribe;
import com.example.dnrxlibrary.Observer;

public class MainActivity extends AppCompatActivity {

    private String TAG = "AppCompatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable
                .create(new ObservableOnSubscribe<Fruit>() {
                    @Override
                    public void onSubscribe(Observer<? super Fruit> observer) {
                        Log.e("RXJava","create 的发射器被触发");
                        Bannana bannana = new Bannana();
                        bannana.setName("香蕉");
                        observer.onNext(bannana);
                    }
                })
                .map(new Function<Fruit, String>() {
                    @Override
                    public String apply(Fruit fruit) {
                        Log.e("RXJava","事件转换");
                        return new String(fruit.name);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("RXJava","终极观察者 的onNext被调用");
                        Log.e(TAG, s);
                    }

                    @Override
                    public void onCompelet() {

                    }

                    @Override
                    public void onErroe() {

                    }
                })
        ;
    }


    class Food {

    }

    class Fruit extends Food {
        String name;
    }

    class Bannana extends Fruit {
        public void setName(String name) {
            this.name = name;
        }
    }


}
