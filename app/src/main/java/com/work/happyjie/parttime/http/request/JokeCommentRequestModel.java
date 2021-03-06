package com.work.happyjie.parttime.http.request;

import android.content.Context;

import com.work.happyjie.parttime.api.ApiService;
import com.work.happyjie.parttime.http.response.JokeCommentResult;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.base.BaseJokeRequestModel;

/**
 * Created by llj on 2018/1/5.
 */

public class JokeCommentRequestModel extends BaseJokeRequestModel {

    private long group_id;
    private long item_id;
    private int count = 15;
    private int offset;
    private int enable_image_comment = 1;

    public JokeCommentRequestModel(Context context, long group_id, long item_id, int offset) {
        super(context);
        this.group_id = group_id;
        this.item_id = item_id;
        this.offset = offset;
    }

    public void getData(RequestCallBack<JokeCommentResult> callBack){
        request(ApiService.jokeApiService.getJokeComment(essence, ac, channel, app_name, version_code,
                version_name, device_platform, device_type, device_brand, os_api, os_version, group_id,
                item_id, count, offset, enable_image_comment), callBack);
    }

}