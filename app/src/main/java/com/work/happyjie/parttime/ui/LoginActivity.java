package com.work.happyjie.parttime.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityLoginBinding;
import com.work.happyjie.parttime.helper.LoginHelper;
import com.work.happyjie.parttime.http.response.LoginResponse;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by llj on 2018-03-04 .
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCompositeDisposable = new CompositeDisposable();

        setContentView(R.layout.activity_login);

    }

    @Override
    protected void initView() {
        showContentView();
        setCenterTitle("登录");

    }

    @Override
    protected void initListener() {
        mViewBinding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mViewBinding.etAccount.getText().toString().trim();
                String password = mViewBinding.etPassword.getText().toString().trim();
                LoginHelper.login(mCompositeDisposable, account, password, new LoginHelper.LoginCallBack() {
                    @Override
                    public void loginResult(boolean isSuccess, LoginResponse callBack) {
                        if(isSuccess){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected boolean isShowBackButton() {
        return false;
    }
}
