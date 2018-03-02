package com.work.happyjie.parttime.utils;

import android.widget.Toast;

import com.work.happyjie.parttime.application.DailyInformationApplication;

/**
 * Created by jingbin on 2016/12/14.
 * 单例Toast
 */

public class ToastUtils {

    private static Toast mToast;

    public static void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(DailyInformationApplication.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }
}
