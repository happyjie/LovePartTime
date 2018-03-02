package com.work.happyjie.parttime.model;

import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.bean.NewsDataResult;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.model.base.BaseRequestModel;
import com.work.happyjie.parttime.utils.SharedPreferenceUtils;

/**
 * Created by llj on 2017/12/15.
 */

public class NewsRequestModel extends BaseRequestModel{

    private String category;
    private int refer = 1;
    private int count = 30;
    private long min_behot_time;    //上次请求时间的时间戳
    private long last_refresh_sub_entrance_interval; //本次请求时间的时间戳
    private int loc_mode = 7;
    private long loc_time;  //本地时间
    private double latitude = 23.20; //经度
    private double longitude = 113.30;//纬度
    private String city = "广州";     //当前城市


    public NewsRequestModel(String category) {
        this.category = category;
        this.min_behot_time = SharedPreferenceUtils.getLong(PreferenceConsts.NEWS_LAST_REQUEST_TIME + category, 0L);
        this.last_refresh_sub_entrance_interval = System.currentTimeMillis();
        this.loc_time = last_refresh_sub_entrance_interval;
        SharedPreferenceUtils.putLong(PreferenceConsts.NEWS_LAST_REQUEST_TIME + category, last_refresh_sub_entrance_interval);
    }

    public void getData(RequestCallBack<NewsDataResult> callBack){
        request(ApiService.newsApiService.getNewsData(category, refer, count, min_behot_time, last_refresh_sub_entrance_interval,
                loc_mode, loc_time, latitude, longitude, city), callBack);
    }
}
