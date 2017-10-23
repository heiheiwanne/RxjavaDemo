package com.example;

import com.example.utils.Utils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xmq on 2017/8/24.
 */

public class Test4 {
    public static void main(String args[]) {
       /* Observable.create((ObservableOnSubscribe<Integer>) e -> {
            for (int j = 0; j < 10; j++) {
                e.onNext(j);
            }
        })
                .flatMap(integer -> {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        list.add("hi:" + integer);
                    }
                    Utils.printStr(list);
                    return Observable.fromIterable(list);
                })
                .subscribe(Utils::printStr);*/


      /* Observable.defer(new Callable<ObservableSource<String>>() {
           @Override
           public ObservableSource<String> call() throws Exception {
               return Observable.just("hello");
           }
       }).subscribe(new Consumer<String>() {
           @Override
           public void accept(String s) throws Exception {
               Utils.printStr(s);
           }
       });*/



      /*Observable.interval(2,TimeUnit.SECONDS,Schedulers.trampoline())
              .subscribe(new Consumer<Long>() {
          @Override
          public void accept(Long aLong) throws Exception {
              Utils.printStr(aLong);
          }
      });*/

        List<Integer> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
      /*Observable.just(list).flatMap(new Function<List<Integer>, ObservableSource<Integer>>() {
          @Override
          public ObservableSource<Integer> apply(@NonNull List<Integer> integers) throws Exception {
              return Observable.fromIterable(integers);
          }
      }).filter(new Predicate<Integer>() {
          @Override
          public boolean test(@NonNull Integer integer) throws Exception {
              if (integer>5) {
                  return true;
              }
              return false;
          }
      }).subscribe(new Consumer<Integer>() {
          @Override
          public void accept(Integer integer) throws Exception {
              Utils.printStr(integer);
          }
      });*/


        Observable.just(list).flatMap(new Function<List<Integer>, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(@NonNull List<Integer> integers) throws Exception {
                return Observable.fromIterable(integers);
            }
        })
                .take(5)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Utils.printStr("准备：" + integer);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Utils.printStr(integer);
                    }
                });

    }
}
