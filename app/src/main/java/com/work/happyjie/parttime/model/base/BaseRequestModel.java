package com.work.happyjie.parttime.model.base;

import com.orhanobut.logger.Logger;
import com.work.happyjie.parttime.http.RequestCallBack;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by llj on 2017/12/13.
 */

public abstract class BaseRequestModel {

    protected  <T> void request(Observable<T> observable, RequestCallBack<T> callBack) {
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.getMessage(), "onError()");
                        callBack.onError(e);
                    }

                    @Override
                    public void onNext(T t) {
                        Logger.d("onNext()");
                        callBack.onSuccess(t);
                    }
                });
        callBack.returnSubscription(subscription);
    }
}
