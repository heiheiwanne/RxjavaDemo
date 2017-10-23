package com.okay.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Observable<String>() {

            @Override
            protected void subscribeActual(Observer<? super String> observer) {

            }
        }.subscribeOn(AndroidSchedulers.mainThread());


    }
}
