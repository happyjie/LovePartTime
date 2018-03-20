package com.work.happyjie.parttime.http.request;

import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseRequestModel;
import com.work.happyjie.parttime.http.response.LoginResponse;
import com.work.happyjie.parttime.http.response.base.BaseResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class LoginRequestModel extends BaseRequestModel {
    private String username;
    private String password;

    public LoginRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void login(RequestCallBack<LoginResponse> callBack){
        request(ApiService.partTimeService.login(this), callBack);
    }
}
