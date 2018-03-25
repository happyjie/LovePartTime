package com.work.happyjie.parttime.http.request;

import com.lib.llj.utils.EnCoderUtils;
import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseRequestModel;
import com.work.happyjie.parttime.http.response.GetTaskListResponse;
import com.work.happyjie.parttime.http.response.base.BaseResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class GetTaskListRequestModel extends BaseRequestModel {
    private String username;
    private int finishstate; //1-未领取 2-未完成 3-已完成 4-已过期
    private int currPage;
    private int pageSize = 20;


    public GetTaskListRequestModel(int finishstate, int currPage) {
        this.username = SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT);
        this.finishstate = finishstate;
        this.currPage = currPage;

    }

    public void getTaskList(RequestCallBack<GetTaskListResponse> callBack){
        request(ApiService.partTimeService.getTaskList(username, finishstate, currPage, pageSize),
                callBack);
    }
}
