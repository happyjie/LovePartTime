package com.work.happyjie.parttime.http.request;

import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseRequestModel;
import com.work.happyjie.parttime.http.response.GetHomeDataResponse;
import com.work.happyjie.parttime.http.response.LoginResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class GetHomeDataRequestModel extends BaseRequestModel {
    private String username;

    public GetHomeDataRequestModel(String username) {
        this.username = username;
    }

    public void getHomeData(RequestCallBack<GetHomeDataResponse> callBack){
        request(ApiService.partTimeService.getHomeData(username), callBack);
    }
}
