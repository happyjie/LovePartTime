package com.work.happyjie.parttime.application;

import android.app.Application;

import com.lib.llj.http.HttpClient;
import com.orhanobut.logger.Logger;
import com.work.happyjie.parttime.BuildConfig;
import com.work.happyjie.parttime.utils.SharedPreferenceUtils;

/**
 * Created by llj on 2017/12/7.
 */

public class DailyInformationApplication extends Application {

    private static final String TAG = DailyInformationApplication.class.getSimpleName();
    private static DailyInformationApplication dailyInformationApplication;

    public static DailyInformationApplication getInstance() {
        return dailyInformationApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dailyInformationApplication = this;

        //初始化SharedPreference
        SharedPreferenceUtils.initPreference(this);

        HttpClient.getInstance().init(getApplicationContext(), BuildConfig.DEBUG);

        //设置日志打印的tag
        Logger.init(TAG);
    }
}
