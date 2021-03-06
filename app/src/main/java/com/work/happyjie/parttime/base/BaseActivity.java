package com.work.happyjie.parttime.base;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lib.llj.utils.DangerousPermissionUtils;
import com.lib.llj.utils.SingleClickListener;
import com.lib.llj.utils.StatusBarUtil;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.databinding.ActivityBaseBinding;
import com.work.happyjie.parttime.widget.LoadingDialog;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by llj on 2017/12/8.
 */
public class BaseActivity<VDB extends ViewDataBinding> extends AppCompatActivity {

    protected ActivityBaseBinding mBaseBinding;
    protected VDB mViewBinding;
    protected LinearLayout llLoading;
    protected LinearLayout llError;
    protected AnimationDrawable mAnimationDrawable;
    protected CompositeDisposable mCompositeDisposable;

    protected DangerousPermissionUtils permissionUtil;
    protected LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        permissionUtil = new DangerousPermissionUtils(this);
        permissionUtil.checkDangerousPermissions(
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});

        loadingDialog = new LoadingDialog(this, R.style.dialogstyle);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
//        super.setContentView(layoutResID);
        mBaseBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_base, null, false);
        mViewBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        //将传入的布局设置为match_parent
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mViewBinding.getRoot().setLayoutParams(layoutParams);

        mBaseBinding.container.addView(mViewBinding.getRoot());
        super.setContentView(mBaseBinding.getRoot());

        llLoading = mBaseBinding.llProgressBar;
        llError = mBaseBinding.llError;

        //设置透明状态栏
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorTheme), 0);

        setToolBar();

        // 点击加载失败布局
        llError.setOnClickListener(new SingleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showInitLoadingView();
                onRefresh();
            }
        });

        //先隐藏content布局
        mViewBinding.getRoot().setVisibility(View.GONE);

        initView();
        initListener();
    }

    protected void initView(){

    }

    protected void initListener(){
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(mBaseBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(isShowBackButton());
            actionBar.setHomeAsUpIndicator(getToolBarLeftIcon());
        }

        mBaseBinding.toolBar.setNavigationOnClickListener(getToolBarLeftIconClickListener());
    }

    /**
     * 设置toolbar是否显示
     * @param visible
     */
    protected void setToolbarVisible(boolean visible){
        mBaseBinding.toolBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置是否显示居中标题
     * @param visible
     */
    protected void setCenterTitleVisible(boolean visible){
        mBaseBinding.tvTitle.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }


    protected boolean isShowBackButton(){
        return true;
    }


    /**
     * 显示标题栏左侧标题，默认是返回按钮
     * @return
     */
    protected int getToolBarLeftIcon(){
        return R.drawable.icon_back;
    }

    /**
     * 设置标题栏左侧按钮的点击事件 默认是返回键
     * @return
     */
    protected View.OnClickListener getToolBarLeftIconClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
    }

    /**
     * 居中标题设置
     * @param title
     */
    protected void setCenterTitle(String title){
        mBaseBinding.tvTitle.setText(title);
    }

    public void setTitle(CharSequence text) {
        mBaseBinding.toolBar.setTitle(text);
    }

    /**
     * 数据刷新
     */
    protected void onRefresh() {

    }

    /**
     * 加载动画
     */
    protected void showInitLoadingView() {
        if(null == mAnimationDrawable) {
            mAnimationDrawable = (AnimationDrawable) mBaseBinding.imgProgress.getDrawable();
        }

        if (llLoading.getVisibility() != View.VISIBLE) {
            llLoading.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (mViewBinding.getRoot().getVisibility() != View.GONE) {
            mViewBinding.getRoot().setVisibility(View.GONE);
        }
        if (llError.getVisibility() != View.GONE) {
            llError.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (llLoading.getVisibility() != View.GONE) {
            llLoading.setVisibility(View.GONE);
        }
        // 停止动画
        if (null != mAnimationDrawable && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (llError.getVisibility() != View.GONE) {
            llError.setVisibility(View.GONE);
        }
        if (mViewBinding.getRoot().getVisibility() != View.VISIBLE) {
            mViewBinding.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (llLoading.getVisibility() != View.GONE) {
            llLoading.setVisibility(View.GONE);
        }
        // 停止动画
        if (null != mAnimationDrawable && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (llError.getVisibility() != View.VISIBLE) {
            llError.setVisibility(View.VISIBLE);
        }
        if (mViewBinding.getRoot().getVisibility() != View.GONE) {
            mViewBinding.getRoot().setVisibility(View.GONE);
        }
    }

    protected void showLoading() {
        if (loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(this, R.style.dialogstyle);
        }
        loadingDialog.show();
    }

    protected void dismissLoading() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    protected <V extends View> V getView(@IdRes int id){
        return (V) findViewById(id);
    }

    public void addSubscription(Disposable disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    public void removeSubscription(Disposable disposable) {
        if (this.mCompositeDisposable != null) {
            this.mCompositeDisposable.remove(disposable);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeDisposable != null) {
            this.mCompositeDisposable.clear();
        }
    }
}
