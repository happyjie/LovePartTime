package com.work.happyjie.parttime.ui.parttime.finance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.lib.llj.utils.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityFinanceDetailBinding;
import com.work.happyjie.parttime.databinding.ActivityIncomeDetailBinding;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.DrawCashRequestModel;
import com.work.happyjie.parttime.http.request.GetFinanceInfoRequestModel;
import com.work.happyjie.parttime.http.request.GetIncomingDetailRequestModel;
import com.work.happyjie.parttime.http.response.GetFinanceInfoResponse;
import com.work.happyjie.parttime.http.response.GetIncomDetailResponse;
import com.work.happyjie.parttime.http.response.base.BaseResponse;
import com.work.happyjie.parttime.ui.parttime.incom_detail.IncomeDetailAdapter;
import com.work.happyjie.parttime.widget.filter.ExpandTabFilterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by asus on 2018-03-21 .
 */

public class FinanceDetailActivity extends BaseActivity<ActivityFinanceDetailBinding> {
    private FinanceDetailAdapter mAdapter;

    private int mcurPage = 1;

    private ArrayList<View> mViewArray;
    private ArrayList<String> mTextArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_detail);
    }

    @Override
    protected void initView() {
        setCenterTitle("财务信息");
        showInitLoadingView();

        mViewBinding.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));

        mAdapter = new FinanceDetailAdapter(this);
        mViewBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        mViewBinding.recycleView.setAdapter(mAdapter);

        getData(false);
    }

    @Override
    protected void initListener() {
        mViewBinding.smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mcurPage++;
                getData(false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mcurPage = 1;
                getData(false);
            }
        });

        mViewBinding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mViewBinding.etMoney.getText().toString().trim();
                if (TextUtils.isEmpty(str)) {
                    ToastUtils.showShort("请先输入要提现的金额");
                    return;
                }

                float money = 0.0f;
                try {
                    money = Float.parseFloat(str);
                } catch (NumberFormatException e) {
                    ToastUtils.showShort("请输入正确的金额");
                    return;
                }

                drawCash(money);
            }
        });
    }


    private void drawCash(float money) {
        showLoading();
        DrawCashRequestModel drawCashRequestModel = new DrawCashRequestModel(money);
        drawCashRequestModel.commit(new RequestCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse result) {
                dismissLoading();
                mViewBinding.smartRefreshLayout.finishLoadmore();
                mViewBinding.smartRefreshLayout.finishRefresh();
                if (result.isSuccess()) {
                    ToastUtils.showShort("申请成功");

                    //申请成功后，重新刷新数据，更新可提现金额数值
                    getData(false);

                } else {
                    ToastUtils.showShort(TextUtils.isEmpty(result.getErrorMsg()) ? "申请失败" : result.getErrorMsg());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                dismissLoading();
                mViewBinding.smartRefreshLayout.finishLoadmore();
                mViewBinding.smartRefreshLayout.finishRefresh();
                ToastUtils.showShort(throwable.getMessage());
            }

            @Override
            public void returnSubscription(Disposable disposable) {
                addSubscription(disposable);
            }
        });
    }

    private void getData(boolean isShowLoading) {
        if (isShowLoading) showLoading();
        GetFinanceInfoRequestModel model;
        model = new GetFinanceInfoRequestModel(mcurPage);
        model.getData(new RequestCallBack<GetFinanceInfoResponse>() {
            @Override
            public void onSuccess(GetFinanceInfoResponse result) {
                dismissLoading();

                if (null == result) {
                    if (1 == mcurPage) {
                        showError();
                    } else {
                        mcurPage--;
                    }
                    return;
                }

                showContentView();

                mViewBinding.tvTotalMoney.setText(result.getTotalrow().get(0).getTotalincome());
                mViewBinding.tvEnableMoney.setText(result.getTotalrow().get(0).getBalance());

                if (1 == result.getCurrPage()) {
                    mAdapter.clear();
                }

                if (null != result.getLists()) {
                    mAdapter.addAll(result.getLists());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                dismissLoading();
                if (1 == mcurPage) {
                    showError();
                } else {
                    mcurPage--;
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
