package com.work.happyjie.parttime.http.request;

import android.content.Context;

import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.http.response.JokeContentTypeResult;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseJokeRequestModel;

/**
 * Created by llj on 2018/1/3.
 */

public class JokeContentTypeRequestModel extends BaseJokeRequestModel {

    public JokeContentTypeRequestModel(Context context) {
        super(context);
    }

    public void getData(RequestCallBack<JokeContentTypeResult> callBack){
        request(ApiService.jokeApiService.getJokeContentType(essence, ac, channel, app_name, version_code,
                version_name, device_platform, device_type, device_brand, os_api, os_version), callBack);
    }
}
