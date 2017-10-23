package com.okay.rxjavademo.rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xmq on 2017/8/24.
 */

public class Test12 {
    public static void main(String args[]){
        Flowable.range(1,10).subscribe(new Subscriber<Integer>() {
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
        });
    }
}
