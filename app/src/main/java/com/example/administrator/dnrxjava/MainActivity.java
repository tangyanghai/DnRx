package com.example.administrator.dnrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
                        observer.onNext(new Bannana());
                    }
                })
                .subscribe(new Observer<Food>() {
                    @Override
                    public void onNext(Food fruit) {

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

    }

    class Bannana extends Fruit {

    }


}
