package com.example;


import com.example.utils.Utils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xmq on 2017/8/21.
 */

public class Test3 {
    public static void main(String args[]) {
        List<String> list = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            list.add(j, j + ":position");
        }

        /*Observable.create((ObservableOnSubscribe<String>) e -> {
            for (int i = 0; i < list.size(); i++) {
//                e.onNext(list.get(i));
                Utils.printStr(list.get(i));
            }
        }).subscribe()*/
        ;


        /*Observable.fromArray(list).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<String> strings) {
                    Utils.printStr(strings);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        //fail
        /*Observable.interval(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Utils.printStr("订阅");
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Utils.printStr(aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Utils.printStr("onError");
                    }

                    @Override
                    public void onComplete() {
                        Utils.printStr("onComplete");
                    }
                });*/


        /*Flowable.range(1,10).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("OnSubscribe start");
                s.request(Long.MAX_VALUE);
                System.out.println("OnSubscribe end");
            }

            @Override
            public void onNext(Integer v) {
                System.out.println(v);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        });*/

        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int i = 0; i < 100; i++) {
                Utils.printStr("emitter:"+ i);
                e.onNext(i);
            }
        }, BackpressureStrategy.BUFFER)
                .map(integer -> "this is :"+integer)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    private Subscription sub;
                    @Override
                    public void onSubscribe(Subscription s) {
                        sub =s;
                        Utils.printStr("start");
                        s.request(1000);
                        Utils.printStr("end");
                    }

                    @Override
                    public void onNext(String s) {
                        Utils.printStr(s);
//                        sub.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Utils.printStr("onError");
                    }

                    @Override
                    public void onComplete() {
                        Utils.printStr("onComplete");
                    }
                });

    }
}
