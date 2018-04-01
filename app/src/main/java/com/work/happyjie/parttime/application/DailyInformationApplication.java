package com.work.happyjie.parttime.application;

import android.app.Application;

import com.lib.llj.CommonlibApp;
import com.lib.llj.http.HttpClient;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.work.happyjie.parttime.BuildConfig;
import com.work.happyjie.parttime.consts.GlobalConsts;

/**
 * Created by llj on 2017/12/7.
 */

public class DailyInformationApplication extends Application {

    private static final String TAG = DailyInformationApplication.class.getSimpleName();
    private static DailyInformationApplication dailyInformationApplication;

    //微信api
    private IWXAPI wxApi;

    public static DailyInformationApplication getInstance() {
        return dailyInformationApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dailyInformationApplication = this;


        CommonlibApp.init(this);

        HttpClient.getInstance().init(getApplicationContext(), BuildConfig.DEBUG);

//        wxApi = WXAPIFactory.createWXAPI(this, GlobalConsts.WECHAT_APP_ID, true);
//        wxApi.registerApp(GlobalConsts.WECHAT_APP_ID);

        //设置日志打印的tag
        Logger.init(TAG);
    }

}
