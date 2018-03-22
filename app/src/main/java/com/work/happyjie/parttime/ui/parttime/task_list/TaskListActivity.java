package com.work.happyjie.parttime.ui.parttime.task_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityTaskListBinding;
import com.work.happyjie.parttime.ui.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llj on 2018/3/22.
 */

public class TaskListActivity extends BaseActivity<ActivityTaskListBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
    }

    @Override
    protected void initView() {
        super.initView();
        setCenterTitle("任务列表");
        iniFragment();

        showContentView();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    private void iniFragment(){
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        String titles[] = new String[]{"未领取","未完成","已完成","已过期","全部"};
        int types[] = new int[]{1, 2, 3, 4, 0};

        for(int i = 0; i < titles.length; i++){
            titleList.add(titles[i]);
            fragmentList.add(TaskListFragment.getInstance(types[i], titles[i]));
        }

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        mViewBinding.vpTasks.setAdapter(fragmentPagerAdapter);
        mViewBinding.vpTasks.setCurrentItem(0);
        mViewBinding.vpTasks.setOffscreenPageLimit(5);
        mViewBinding.tableLayout.setupWithViewPager(mViewBinding.vpTasks);
        mViewBinding.tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
