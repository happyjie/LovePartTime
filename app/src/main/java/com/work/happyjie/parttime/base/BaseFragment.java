package com.work.happyjie.parttime.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lib.llj.utils.SingleClickListener;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.databinding.FragmentBaseBinding;
import com.work.happyjie.parttime.widget.LoadingDialog;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by llj on 2017-12-11 .
 */

public abstract class BaseFragment<VDB extends ViewDataBinding> extends Fragment{
    protected VDB mViewBinding;
    protected FragmentBaseBinding mBaseBinding;
    protected LinearLayout llLoading;
    protected LinearLayout llError;
    protected AnimationDrawable mAnimationDrawable;
    private CompositeDisposable mCompositeDisposable;

    // fragment是否显示了
    protected boolean mIsVisible = false;
    protected Activity mActivity;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        loadingDialog = new LoadingDialog(mActivity, R.style.dialogstyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, null);
        mBaseBinding = DataBindingUtil.bind(view);

        mViewBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContentView(), null, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        mViewBinding.getRoot().setLayoutParams(layoutParams);
        mBaseBinding.container.addView(mViewBinding.getRoot());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        llLoading = mBaseBinding.llProgressBar;
        llError = mBaseBinding.llError;
        // 点击加载失败布局
        llError.setOnClickListener(new SingleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showInitLoadingView();
                onRefresh();
            }
        });
        mViewBinding.getRoot().setVisibility(View.GONE);

        initView();
        initListener();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisible = isVisibleToUser;
        if(isVisibleToUser){
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     *  设置布局
     * @return
     */
    protected abstract int setContentView();

    protected void initView(){

    }

    protected void initListener(){

    }


    /**
     * Fragment变为可见时调用此方法
     */
    protected void onVisible(){

    }

    /**
     * Fragment变为不可见时调用此方法
     */
    protected void onInvisible(){

    }

    /**
     * 加载失败后点击后的操作
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

    public void showLoading() {
        if (!isAccessable()) return;
        if (loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(mActivity, R.style.dialogstyle);
        }
        loadingDialog.show();
    }

    public void dismissLoading() {
        if (!isAccessable()) return;
        if (loadingDialog != null && !getActivity().isFinishing() && !isDetached() && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    /**
     * 当前fragment是否可访问
     * @return
     */
    protected boolean isAccessable(){
        if (null == BaseFragment.this || isDetached()
                || null == getActivity() || getActivity().isFinishing()){
            return  false;
        }
        return true;
    }


    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    public void addSubscription(Disposable disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    public void removeSubscription(Disposable s) {
        if (this.mCompositeDisposable != null) {
            this.mCompositeDisposable.remove(s);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeDisposable != null) {
            this.mCompositeDisposable.clear();
        }
    }
}
