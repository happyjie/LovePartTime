package com.work.happyjie.parttime.http.request;

import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseRequestModel;
import com.work.happyjie.parttime.http.response.GetIncomDetailResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class GetIncomingDetailRequestModel extends BaseRequestModel {
    private String username;

    private int year; //：年份
    private int month; //：月
    private int currPage; //：当前页数
    private int pageSize = 20; //:每页显示的记录数"

    public GetIncomingDetailRequestModel(int year, int month, int currPage) {
        this.username = SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT);
        this.year = year;
        this.month = month;
        this.currPage = currPage;
    }

    public void getData(RequestCallBack<GetIncomDetailResponse> callBack){
        request(ApiService.partTimeService.getIncomingDetail(username, year, month, currPage, pageSize),
                callBack);
    }
}
