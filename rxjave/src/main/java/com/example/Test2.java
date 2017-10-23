package com.example;

import com.example.utils.Utils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by xmq on 2017/8/21.
 */

public class Test2 {
    public static void main(String args[]) {

        /**
        Flowable.range(0,9)
                .subscribe(new Subscriber<Integer>() {
                    Subscription mSubscriber;
                    @Override
                    public void onSubscribe(Subscription s) {
                        Utils.printStr("start");
                        mSubscriber = s;
                        mSubscriber.request(2); //尽量将初始化在此之上完成
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Utils.printStr(integer);
//                        mSubscriber.request(2);
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
//                    */


//        /** 使用create
         Flowable.create(new FlowableOnSubscribe<Integer>() {

             @Override
             public void subscribe(@NonNull FlowableEmitter<Integer> e) throws Exception {

             }
         } , BackpressureStrategy.BUFFER);
//         */

    }

}
