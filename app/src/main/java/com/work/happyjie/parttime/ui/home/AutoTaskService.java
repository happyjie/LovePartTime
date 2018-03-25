package com.work.happyjie.parttime.ui.home;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.eventbus.AutoTaskEvent;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.AutoTaskRequestModel;
import com.work.happyjie.parttime.http.response.base.BaseResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.disposables.Disposable;

/**
 * Created by asus on 2018-03-24 .
 */

public class AutoTaskService extends Service {


    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        EventBus.getDefault().register(this);
        new AutoTaskThread().start();
        Log.i("llj", "onBind()");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        EventBus.getDefault().unregister(this);
        Log.i("llj", "onUnBind()");
        return super.onUnbind(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AutoTaskEvent messageEvent) {
        autoTask();
    }
    private void autoTask(){
        AutoTaskRequestModel model = new AutoTaskRequestModel();
        model.commit(new RequestCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse result) {
                SharedPreferencesUtils.putLong(PreferenceConsts.LAST_AUTO_TASK_TIME, System.currentTimeMillis());
            }

            @Override
            public void onError(Throwable throwable) {
                //刷单失败也更新这个时间，避免因服务器异常导致的请求失败后无限请求
                SharedPreferencesUtils.putLong(PreferenceConsts.LAST_AUTO_TASK_TIME, System.currentTimeMillis());
            }

            @Override
            public void returnSubscription(Disposable disposable) {

            }
        });
    }

    class AutoTaskThread extends Thread {
        @Override
        public void run() {
            while (true) {
                long lastTime = SharedPreferencesUtils.getLong(PreferenceConsts.LAST_AUTO_TASK_TIME);
                long curTime = System.currentTimeMillis();

                if (curTime - lastTime > 1000 * 60 * 5) {
                    EventBus.getDefault().post(new AutoTaskEvent());
                }

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class MyBinder extends Binder {
        public AutoTaskService getService() {
            return AutoTaskService.this;
        }
    }
}
