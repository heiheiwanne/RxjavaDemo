package com.example;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Test1 {

    public static void main(String atgs[]) {
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                printStr("emitter: 1  ");
                emitter.onNext(1);
                printStr("emitter: 2  ");
                emitter.onNext(2);
                printStr("emitter: 3  ");
                emitter.onNext(3);
                printStr("emitter: complete  ");
                emitter.onComplete();
                printStr("emitter: 4  ");
                emitter.onNext(4);
            }})
                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.single())





                /**  空参数 subscribe() 不关心下游，上游只管发送数据
             .subscribe();
//             */
//            /**  subscribe(Observer<? super T> observer)  下游关心上游所有的事件
        .subscribe(new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
                disposable = d;
                printStr("subscribe");
                printStr(d.isDisposed() + "");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                if (integer == (2)) {
                    compositeDisposable.dispose();
                    printStr(compositeDisposable.isDisposed() + "");
                }
                printStr(integer + "");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                printStr(e.getMessage());
            }

            @Override
            public void onComplete() {
                printStr("comlete");
            }
        });
//        */



        /** subscribe(Consumer<? super T> onNext)  只关心onNext ，当然你发送的complete会断开链接
         .subscribe(new Consumer<Integer>() {
             @Override
             public void accept(Integer integer) throws Exception {
                 printStr(integer + "");
             }
         });
//         */


        /**
          .subscribe(new Consumer<Integer>() { //onNext
              @Override
              public void accept(Integer integer) throws Exception {
                printStr(integer);
              }
          }, new Consumer<Throwable>() { //onError
              @Override
              public void accept(Throwable throwable) throws Exception {
                  throwable.printStackTrace();
                printStr(throwable.getMessage());
              }
          }, new Action() {  //onComplete
              @Override
              public void run() throws Exception {
                  printStr("recv complete");
              }
          });
//         */


    }




    public static void printStr(Object str) {
        System.out.print(String.valueOf(str)  +"    thread:"+ Thread.currentThread().getName()+ "\n");
    }
}
