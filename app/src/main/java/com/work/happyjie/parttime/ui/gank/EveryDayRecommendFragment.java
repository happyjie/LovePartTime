package com.work.happyjie.parttime.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseFragment;
import com.work.happyjie.parttime.databinding.FragmentEverydayRecommendBinding;

/**
 * Created by llj on 2017/12/12.
 * 每日推荐
 */

public class EveryDayRecommendFragment extends BaseFragment<FragmentEverydayRecommendBinding> {
    @Override
    protected int setContentView() {
        return R.layout.fragment_everyday_recommend;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }
}
