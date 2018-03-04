package com.work.happyjie.parttime.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityLoginBinding;

/**
 * Created by asus on 2018-03-04 .
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void initView() {
        showContentView();
        setCenterTitle("登录");

    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected boolean isShowBackButton() {
        return false;
    }
}
