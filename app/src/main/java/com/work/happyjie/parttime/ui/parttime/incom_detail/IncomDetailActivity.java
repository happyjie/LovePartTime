package com.work.happyjie.parttime.ui.parttime.incom_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.lib.llj.utils.ToastUtils;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityIncomeDetailBinding;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.GetIncomingDetailRequestModel;
import com.work.happyjie.parttime.http.response.GetIncomDetailResponse;

import io.reactivex.disposables.Disposable;

/**
 * Created by asus on 2018-03-21 .
 */

public class IncomDetailActivity extends BaseActivity<ActivityIncomeDetailBinding> {
    private IncomeDetailAdapter mAdapter;

    private int curPage  = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);
    }

    @Override
    protected void initView() {
        setCenterTitle("收入明细");
        showLoading();

        mAdapter = new IncomeDetailAdapter(this);
        mViewBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        mViewBinding.recycleView.setAdapter(mAdapter);

        getData("2018", "3", curPage);
    }

    @Override
    protected void initListener() {
    }

    private void getData(String year, String month, int page){
        GetIncomingDetailRequestModel model;
        model = new GetIncomingDetailRequestModel(year, month, curPage);
        model.getData(new RequestCallBack<GetIncomDetailResponse>() {
            @Override
            public void onSuccess(GetIncomDetailResponse result) {

                if(null == result && 1 == page){
                    showError();
                    return;
                }

                showContentView();

                if(1 == result.getCurrPage()){
                    mAdapter.clear();
                }

                if(null != result.getLists()){
                    mAdapter.addAll(result.getLists());
                }

            }

            @Override
            public void onError(Throwable throwable) {
                if(1 == page){
                    showError();
                } else {
                    ToastUtils.showShort(throwable.getMessage());
                }

            }

            @Override
            public void returnSubscription(Disposable disposable) {
                addSubscription(disposable);
            }
        });
    }
}
