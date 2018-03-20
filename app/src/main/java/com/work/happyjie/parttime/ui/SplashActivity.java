package com.work.happyjie.parttime.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.consts.ConstantsImageUrl;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.databinding.ActivitySplashBinding;
import com.work.happyjie.parttime.helper.LoginHelper;
import com.work.happyjie.parttime.http.response.LoginResponse;

import java.util.Random;

/**
 * Created by llj on 2017/12/7.
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private boolean isLoginSuccess = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    @Override
    protected void initView() {
        // 先显示默认图
        mViewBinding.ivDefaultPic.setImageResource(R.drawable.img_splash_default);

        int i = new Random().nextInt(ConstantsImageUrl.TRANSITION_URLS.length);
        Glide.with(this)
                .load(ConstantsImageUrl.TRANSITION_URLS[i])
                .placeholder(R.drawable.img_splash_default)
                .error(R.drawable.img_splash_default)
                .into(mViewBinding.ivPic);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.transition_anim);
        animation.setAnimationListener(animationListener);
        mViewBinding.ivDefaultPic.startAnimation(animation);

//        mViewBinding.ivDefaultPic.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                gotoMainActivity();
//            }
//        }, 3000);

//        mViewBinding.tvJump.setOnClickListener(v -> gotoMainActivity());
    }


    /**
     * 动画监听
     */
    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            String account = SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT);
            String password = SharedPreferencesUtils.getString(PreferenceConsts.PASSWORD);
            if(!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)){
                LoginHelper.login(mCompositeDisposable, account, password, new LoginHelper.LoginCallBack() {
                    @Override
                    public void loginResult(boolean isSuccess, LoginResponse callBack) {
                        isLoginSuccess = isSuccess;
                        SharedPreferencesUtils.putBoolean(PreferenceConsts.LOGIN_STATUS, isLoginSuccess);
                    }
                });
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isLoginSuccess){
                gotoMainActivity();
            } else {
                gotoLoginActivity();
            }

//            mViewBinding.ivDefaultPic.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };


    private boolean isGotoMainActivity = false;
    private void gotoMainActivity(){
        if (isGotoMainActivity) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isGotoMainActivity = true;
    }

    private void gotoLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
    }
}
