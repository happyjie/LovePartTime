package com.work.happyjie.parttime.model;

import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.bean.GankIoDataResult;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.model.base.BaseRequestModel;

/**
 * Created by llj on 2017/12/13.
 */

public class GetGankIoRequestModel extends BaseRequestModel{
    private String type;
    private int per_page = 30;
    private int page;

    public GetGankIoRequestModel(String type, int page) {
        this.type = type;
        this.page = page;
    }

    public void getData(RequestCallBack<GankIoDataResult> callBack){
        request(ApiService.gankApiService.getGankIoData(type, per_page, page), callBack);
    }

}
