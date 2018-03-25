package com.work.happyjie.parttime.enums;

/**
 * Created by asus on 2018-03-24 .
 */

public enum TaskTypeEnum {
    SHARE_TASK(1),  //分享任务
    AUTO_TASK(2), //自动任务
    OFFLINE_TASK(3);//线下任务

    public int typeId;

    TaskTypeEnum(int typeId) {
        this.typeId = typeId;
    }
}
