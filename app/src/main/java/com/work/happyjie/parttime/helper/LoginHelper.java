package com.work.happyjie.parttime.helper;

import android.text.TextUtils;

import com.lib.llj.utils.SharedPreferencesUtils;
import com.lib.llj.utils.ToastUtils;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.LoginRequestModel;
import com.work.happyjie.parttime.http.response.LoginResponse;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by llj on 2018/3/20.
 */

public class LoginHelper {
    public static void login(CompositeDisposable compositeDisposable, String account, String password, LoginCallBack callBack){

        if(TextUtils.isEmpty(account)){
            ToastUtils.showShort("请输入账号");
            return;
        }

        if(TextUtils.isEmpty(password)){
            ToastUtils.showShort("请输入密码");
            return;
        }

        LoginRequestModel model = new LoginRequestModel(account, password);
        model.login(new RequestCallBack<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse result) {
                if(null == result){
                    if(null != callBack) {
                        callBack.loginResult(false, null);
                    }

                    ToastUtils.showShort("登陆出错，请联系管理员解决");
                    return;
                }

                //保存登陆状态、账号、密码
                SharedPreferencesUtils.putString(PreferenceConsts.ACCOUNT, account);
                SharedPreferencesUtils.putString(PreferenceConsts.PASSWORD, password);
                SharedPreferencesUtils.putBoolean(PreferenceConsts.LOGIN_STATUS, true);

                //保存联系我们的相关信息
                SharedPreferencesUtils.putString(PreferenceConsts.CUSTOMER_PHONE, result.getAdminList().getTelephone());
                SharedPreferencesUtils.putString(PreferenceConsts.CUSTOMER_QQ, result.getAdminList().getQqnum());
                SharedPreferencesUtils.putString(PreferenceConsts.CUSTOMER_WECHAT, result.getAdminList().getChartnum());
                SharedPreferencesUtils.putString(PreferenceConsts.COMPANY_ADDRESS, result.getAdminList().getAddress());

                //保存用户详细信息
                UserInfoHelper.getInstance().saveUserInfo(result.getUserList());

                if(callBack != null){
                    callBack.loginResult(true, result);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                SharedPreferencesUtils.putBoolean(PreferenceConsts.LOGIN_STATUS, false);

                UserInfoHelper.getInstance().clearUserInfo();

                if(null != callBack) {
                    callBack.loginResult(false, null);
                }
            }

            @Override
            public void returnSubscription(Disposable disposable) {
                compositeDisposable.add(disposable);
            }
        });
    }



    public interface LoginCallBack{
        void loginResult(boolean isSuccess, LoginResponse callBack);
    }

}
