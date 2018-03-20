package com.lib.llj;

import android.app.Application;

import com.lib.llj.utils.SharedPreferencesUtils;

import java.lang.ref.WeakReference;

/**
 * Created by llj on 2018/3/13.
 * appKit全局管理入口
 */

public class CommonlibApp {
    private static WeakReference<Application> mApp;

    public static Application getInstance() {
        return mApp.get();
    }

    public static void init(Application application) {
        mApp = new WeakReference<>(application);
        SharedPreferencesUtils.init(application);
    }

}
