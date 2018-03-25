package com.work.happyjie.parttime.http.response;

import com.work.happyjie.parttime.http.response.base.BaseResponse;

/**
 * Created by asus on 2018-03-20 .
 */

public class GetHomeDataResponse extends BaseResponse {
    private String balance;//": "500.00",//余额(总收益)
    private String todayincome;//": "0.00", //今日收入
    private String totalincome;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTodayincome() {
        return todayincome;
    }

    public void setTodayincome(String todayincome) {
        this.todayincome = todayincome;
    }

    public String getTotalincome() {
        return totalincome;
    }

    public void setTotalincome(String totalincome) {
        this.totalincome = totalincome;
    }
}
