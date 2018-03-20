package com.lib.llj.utils;

import android.app.Application;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.lib.llj.CommonlibApp;

/**
 * Created by chenyk on 2017/7/25.
 * 吐司工具类
 */

public class ToastUtils {

    private static Application getApp() {
        return CommonlibApp.getInstance();
    }

    /**
     * 显示短吐司
     *
     * @param tips
     */
    public static void showShort(String tips) {
        showToast(tips, Toast.LENGTH_SHORT);
    }

    /**
     * 显示长吐司
     *
     * @param tips
     */
    public static void showLong(String tips) {
        showToast(tips, Toast.LENGTH_LONG);
    }

    /**
     * 显示短吐司
     *
     * @param strRes
     */
    public static void showShort(@StringRes int strRes) {
        showToast(getApp().getResources().getString(strRes), Toast.LENGTH_SHORT);
    }

    /**
     * 显示长吐司
     *
     * @param strRes
     */
    public static void showLong(@StringRes int strRes) {
        showToast(getApp().getResources().getString(strRes), Toast.LENGTH_LONG);
    }

    /**
     * 吐司显示
     *
     * @param tips
     * @param time
     */
    public static void showToast(String tips, int time) {
        Toast.makeText(getApp(), tips, time).show();
    }
}
