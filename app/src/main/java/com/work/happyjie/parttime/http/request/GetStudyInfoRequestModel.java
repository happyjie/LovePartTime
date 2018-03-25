package com.work.happyjie.parttime.http.request;

import com.lib.llj.utils.EnCoderUtils;
import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseRequestModel;
import com.work.happyjie.parttime.http.response.GetStudyInfoResponse;
import com.work.happyjie.parttime.http.response.base.BaseResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class GetStudyInfoRequestModel extends BaseRequestModel {
    private String username;

    public GetStudyInfoRequestModel() {
        this.username = SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT);
    }

    public void getInfo(RequestCallBack<GetStudyInfoResponse> callBack){
        request(ApiService.partTimeService.getStudyInfo(username),
                callBack);
    }
}
