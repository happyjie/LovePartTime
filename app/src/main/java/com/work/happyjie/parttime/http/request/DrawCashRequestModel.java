package com.work.happyjie.parttime.http.request;

import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseRequestModel;
import com.work.happyjie.parttime.http.response.base.BaseResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class DrawCashRequestModel extends BaseRequestModel {
    private String username;
    private float drawcashnum;


    public DrawCashRequestModel(float drawcashnum) {
        this.username = SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT);
        this.drawcashnum = drawcashnum;
    }

    public void commit(RequestCallBack<BaseResponse> callBack){
        request(ApiService.partTimeService.drawCash(username, drawcashnum),
                callBack);
    }
}
