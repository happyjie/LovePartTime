package com.work.happyjie.parttime.http.request;

import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseRequestModel;
import com.work.happyjie.parttime.http.response.GetIncomingDetailResponse;
import com.work.happyjie.parttime.http.response.LoginResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class GetIncomingDetailRequestModel extends BaseRequestModel {
    private String username;

    private String year; //：年份
    private String month; //：月
    private int currPage; //：当前页数
    private int pageSize = 20; //:每页显示的记录数"

    public GetIncomingDetailRequestModel(String username, String year, String month, int currPage) {
        this.username = username;
        this.year = year;
        this.month = month;
        this.currPage = currPage;
    }

    public void getData(RequestCallBack<GetIncomingDetailResponse> callBack){
        request(ApiService.partTimeService.getIncomingDetail(username, year, month, currPage, pageSize),
                callBack);
    }
}
