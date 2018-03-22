package com.work.happyjie.parttime.consts;

import android.os.Environment;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.application.DailyInformationApplication;

/**
 * Created by llj on 2017/12/14.
 */

public interface GlobalConsts {
    String PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    String BIG_IMAGE_SAVE_PATH = PATH + "/" + DailyInformationApplication.getInstance().getString(R.string.app_name_english);
}
