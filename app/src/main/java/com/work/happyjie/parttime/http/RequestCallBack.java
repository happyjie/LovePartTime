package com.work.happyjie.parttime.http;

import rx.Subscription;

/**
 * Created by llj on 2017/12/13.
 */

public interface RequestCallBack<T> {
    void onSuccess(T result);

    void onError(Throwable throwable);

    void returnSubscription(Subscription subscription);
}
