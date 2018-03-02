package com.work.happyjie.parttime.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseFragment;
import com.work.happyjie.parttime.databinding.FragmentGankBinding;
import com.work.happyjie.parttime.ui.adapter.MyFragmentPagerAdapter;
import com.work.happyjie.parttime.ui.joke.JokeListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llj on 2017/12/12.
 */

public class GankFragment extends BaseFragment<FragmentGankBinding>{

    @Override
    protected int setContentView() {
        return R.layout.fragment_gank;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initChildFragment();
    }

    private void initChildFragment(){
        List<Fragment> fragmenList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

//        fragmenList.add(new EveryDayRecommendFragment());
        fragmenList.add(new JokeListFragment());
        fragmenList.add(new OtakuWelfareFragment());
        fragmenList.add(new CustomizatoinGankFragment());

        titleList.add("最新段子");
        titleList.add("宅男福利");
        titleList.add("干货定制");

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmenList, titleList);
        mViewBinding.vpGank.setAdapter(fragmentPagerAdapter);
        mViewBinding.vpGank.setCurrentItem(0);
        mViewBinding.vpGank.setOffscreenPageLimit(3);
        mViewBinding.tableLayout.setupWithViewPager(mViewBinding.vpGank);
        mViewBinding.tableLayout.setTabMode(TabLayout.MODE_FIXED);
    }

}
