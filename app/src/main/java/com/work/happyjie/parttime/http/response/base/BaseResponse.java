package com.work.happyjie.parttime.http.response.base;

/**
 * Created by llj on 2018/3/20.
 */

public class BaseResponse {
    protected String code;
    protected String errorMsg;

    public boolean isSuccess(){
        return "0".equals(code);
    }

    public String getErrorMsg(){
        return errorMsg;
    }
}
