package com.work.happyjie.parttime.ui.parttime.incom_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lib.llj.utils.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityIncomeDetailBinding;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.GetIncomingDetailRequestModel;
import com.work.happyjie.parttime.http.response.GetIncomDetailResponse;
import com.work.happyjie.parttime.widget.filter.ExpandTabFilterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by asus on 2018-03-21 .
 */

public class IncomeDetailActivity extends BaseActivity<ActivityIncomeDetailBinding> {
    private IncomeDetailAdapter mAdapter;

    private int mcurPage = 1;
    private int mSelectYear = getYear();
    private int mSelectMonth = getMonth();

    private ExpandTabFilterView yearFilter, monthFilter;
    private ArrayList<View> mViewArray;
    private ArrayList<String> mTextArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);
    }

    @Override
    protected void initView() {
        setCenterTitle("收入明细");
        showInitLoadingView();

        mViewBinding.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));

        mAdapter = new IncomeDetailAdapter(this);
        mViewBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        mViewBinding.recycleView.setAdapter(mAdapter);

        initTabViewData();

        getData(mSelectYear, mSelectMonth, false);
    }

    @Override
    protected void initListener() {
        yearFilter.setOnSelectListener((id, showText) -> onRefreshData(id, yearFilter, showText));
        monthFilter.setOnSelectListener((id, showText) -> onRefreshData(id, monthFilter, showText));
        mViewBinding.smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mcurPage++;
                getData(mSelectYear, mSelectMonth, false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mcurPage = 1;
                getData(mSelectYear, mSelectMonth, false);
            }
        });
    }

    /**
     * 初始化expandtabView数据
     */
    private void initTabViewData() {
        mViewArray = new ArrayList<>();
        mTextArray = new ArrayList<>();

        List<String> yearList = generateYearList();
        yearFilter = new ExpandTabFilterView(this, yearList, yearList);

        List<String> monthIdList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

        monthFilter = new ExpandTabFilterView(this,
                Arrays.asList("1月", "2月", "3月", "4月", "5月","6月", "7月", "8月", "9月", "10月", "11月", "12月"),
                monthIdList);
        mViewArray.add(yearFilter);
        mViewArray.add(monthFilter);
        mTextArray.add("年份");
        mTextArray.add("月份");
        mViewBinding.etvFilter.setValue(mTextArray, mViewArray);

        //设置初始选择的年份为当前年份
        mViewBinding.etvFilter.setTitle(mSelectYear + "年", 0);
        int selectYearPosition = 0;
        for(int i = 0; i < yearList.size(); i++){
            if(Integer.parseInt(yearList.get(i)) == mSelectYear){
                selectYearPosition = i;
                break;
            }
        }

        yearFilter.setSelectedItemNotNotify(selectYearPosition);

        //设置初始选择的月份为当前月份
        mViewBinding.etvFilter.setTitle(mSelectMonth + "月", 1);
        int selectMonthPosition = 0;
        for(int i = 0; i < monthIdList.size(); i++){
            if(Integer.parseInt(monthIdList.get(i)) == mSelectMonth){
                selectMonthPosition = i;
                break;
            }
        }
        monthFilter.setSelectedItemNotNotify(selectMonthPosition);
    }


    /**
     * 生成年份筛选器
     * @return
     */
    private List<String> generateYearList(){
        List<String> list = new ArrayList<>();

        int curYear = getYear();

        int maxYear;

        if(curYear< 2018){
            maxYear = 2030;
        } else {
            maxYear = curYear;
        }

        for(int i = 2017; i <= maxYear; i++){
            list.add(String.valueOf(i));
        }

        return list;
    }

    /**
     * 获取年份
     * @return
     */
    private int getYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     * @return
     */
    private int getMonth(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    private void getData(int year, int month, boolean isShowLoading){
        if(isShowLoading) showLoading();
        GetIncomingDetailRequestModel model;
        model = new GetIncomingDetailRequestModel(year, month, mcurPage);
        model.getData(new RequestCallBack<GetIncomDetailResponse>() {
            @Override
            public void onSuccess(GetIncomDetailResponse result) {
                dismissLoading();
                mViewBinding.smartRefreshLayout.finishLoadmore();
                mViewBinding.smartRefreshLayout.finishRefresh();
                if(null == result && null == result.getLists()){
                    if(1 == mcurPage) {
                        showError();
                    } else {
                        mcurPage--;
                    }
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
                dismissLoading();
                mViewBinding.smartRefreshLayout.finishLoadmore();
                mViewBinding.smartRefreshLayout.finishRefresh();
                if(1 == mcurPage){
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


    /**
     * 根据过滤器选择内容进行数据筛选及更新,显示在头部
     *
     * @param tabView
     * @param showText
     */
    private void onRefreshData(int id, View tabView, String showText) {
        mViewBinding.etvFilter.onPressBack();
        int tabPosition = mViewBinding.etvFilter.getFilterViewPositon(tabView, mViewArray);
        if (tabPosition >= 0 && !mViewBinding.etvFilter.getTitle(tabPosition).equals(showText)) {
            mViewBinding.etvFilter.setTitle(showText, tabPosition);
        } else {
            return;
        }

        switch (tabPosition) {
            case 0://年份
                mSelectYear = id;
                break;
            case 1://月份
                mSelectMonth = id;
                break;
            default:
                break;
        }

        mcurPage = 1;
        getData(mSelectYear, mSelectMonth, true);
    }
}
