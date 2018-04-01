package com.work.happyjie.parttime.helper;

import com.lib.llj.utils.GsonUtil;
import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.bean.UserInfo;
import com.work.happyjie.parttime.consts.PreferenceConsts;

/**
 * Created by llj on 2018/3/20.
 */

public class UserInfoHelper {

    private static UserInfoHelper helper = null;

    private UserInfo info;

    public static UserInfoHelper getInstance() {
        if (helper == null)
            helper = new UserInfoHelper();
        return helper;
    }

    /**
     * 保存用户信息
     * @param userInfo
     */
    public void saveUserInfo(UserInfo userInfo){
        SharedPreferencesUtils.putString(PreferenceConsts.USER_INFO, GsonUtil.bean2json(userInfo));
        this.info = null;
    }

    /**
     * 清除用户信息
     */
    public static void clearUserInfo(){
        SharedPreferencesUtils.putString(PreferenceConsts.USER_INFO, "");
    }

    /**
     * 获取用户信息
     * @return
     */
    public UserInfo getUserInfo() {
        if(null == info){
            info = GsonUtil.json2bean(SharedPreferencesUtils.getString(PreferenceConsts.USER_INFO), UserInfo.class);
        }
        return info;
    }
}
