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

public class AutoTaskRequestModel extends BaseRequestModel {
    private String username;


    public AutoTaskRequestModel() {
        this.username = SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT);
    }

    public void commit(RequestCallBack<BaseResponse> callBack){
        request(ApiService.partTimeService.autoTask(username),
                callBack);
    }
}
