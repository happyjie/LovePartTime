package com.work.happyjie.parttime.http.request;

import com.lib.llj.utils.EnCoderUtils;
import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseRequestModel;
import com.work.happyjie.parttime.http.response.GetIncomDetailResponse;
import com.work.happyjie.parttime.http.response.base.BaseResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class ShareTaskRequestModel extends BaseRequestModel {
    private String username;
    private String taskid; //：任务id
    private long param1;
    private String param2;

    public ShareTaskRequestModel(String taskId) {
        this.username = SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT);
        this.taskid = taskId;
        param1 = System.currentTimeMillis();
        param2 = EnCoderUtils.stringToMD5(username + "_" + param1);
    }

    public void shareTask(RequestCallBack<BaseResponse> callBack){
        request(ApiService.partTimeService.shareTaskSuccess(username, taskid, param1, param2),
                callBack);
    }
}
