package com.example.xuan.rxjava2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.just(1)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer > 0;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("---------->v:", "v:" + integer);
            }
        });
        Observable.concat(
                Arrays.asList(
                        Observable.create(
                                new ObservableOnSubscribe<Object>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                                        Log.d("---------->执行", "1");
                                        Thread.sleep(4_00);
                                        e.onNext("1");
                                        Log.d("---------->执行完", "1");
                                        e.onComplete();
                                    }
                                }).subscribeOn(Schedulers.newThread()),
                        Observable.create(
                                new ObservableOnSubscribe<Object>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                                        Log.d("---------->执行", "3");
                                        Thread.sleep(2_00);
                                        e.onNext("3");
                                        Log.d("---------->执行完", "3");
                                        e.onComplete();
                                    }
                                }).subscribeOn(Schedulers.newThread()),
                        Observable.create(
                                new ObservableOnSubscribe<Object>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                                        Log.d("---------->执行", "2");
                                        Thread.sleep(1_00);
                                        e.onNext("2");
                                        Log.d("---------->执行完", "2");
                                        e.onComplete();
                                    }
                                }).subscribeOn(Schedulers.newThread()))
        )
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("-------->o", "" + o);
                    }
                });
    }
}
