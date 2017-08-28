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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.concat(
                Arrays.asList(
                        Observable.create(
                                new ObservableOnSubscribe<Object>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                                        e.onNext("3");
                                    }
                                }),
                        Observable.create(
                                new ObservableOnSubscribe<Object>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                                        e.onNext("2");
                                    }
                                }),
                        Observable.create(
                                new ObservableOnSubscribe<Object>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                                        e.onNext("1");
                                    }
                                }))
        )
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("-------->o", "" + o);
                    }
                });
    }
}
