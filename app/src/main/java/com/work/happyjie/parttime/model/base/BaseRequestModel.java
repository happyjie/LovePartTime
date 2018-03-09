package com.work.happyjie.parttime.model.base;

import com.orhanobut.logger.Logger;
import com.work.happyjie.parttime.http.RequestCallBack;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by llj on 2017/12/13.
 */

public abstract class BaseRequestModel {

    protected  <T> void request(Observable<T> observable, RequestCallBack<T> callBack) {
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.returnSubscription(d);
                    }

                    @Override
                    public void onNext(T t) {
                        Logger.d("onNext()");
                        callBack.onSuccess(t);
                    }
                });
    }
}
