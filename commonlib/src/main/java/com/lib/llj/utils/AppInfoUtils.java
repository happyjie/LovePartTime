package com.lib.llj.utils;

import android.app.Application;

import com.lib.llj.CommonlibApp;


/**
 * Created by llj on 2018/3/13.
 * 获取App相关信息
 * 如版本号、code等
 */

public class AppInfoUtils {
    
    private static Application getApp() {
        return CommonlibApp.getInstance();
    }

    /**
     * 获取app版本名称
     *
     * @return
     */
    public static String getVersionName() {
        try {
            String pkName = getApp().getPackageName();
            String versionName = getApp().getPackageManager().getPackageInfo(pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取app版本号
     *
     * @return
     */
    public static int getVersionCode() {
        try {
            String pkName = getApp().getPackageName();
            int versionCode = getApp().getPackageManager().getPackageInfo(pkName, 0).versionCode;
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
