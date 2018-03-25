package com.work.happyjie.parttime.http.request;

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

public class ReceiveTaskRequestModel extends BaseRequestModel {
    private String username;
    private String taskid;

    public ReceiveTaskRequestModel(String taskid) {
        this.username = SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT);
        this.taskid = taskid;
    }

    public void receiveTask(RequestCallBack<BaseResponse> callBack){
        request(ApiService.partTimeService.receiveTask(username, taskid),
                callBack);
    }
}
