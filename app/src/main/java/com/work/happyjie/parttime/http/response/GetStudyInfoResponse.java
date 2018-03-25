package com.work.happyjie.parttime.http.response;

import com.work.happyjie.parttime.http.response.base.BaseResponse;

import java.util.List;

/**
 * Created by llj on 2018/3/21.
 */

public class GetStudyInfoResponse extends BaseResponse {

   private String title;
   private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
