package com.work.happyjie.parttime.ui.parttime.person_info;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityPersonInfoBinding;

/**
 * Created by llj on 2018/3/8.
 */

public class PersonInfoActivity extends BaseActivity<ActivityPersonInfoBinding> {

    private PersonInfoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
    }

    @Override
    protected void initView() {
        mAdapter = new PersonInfoAdapter();
    }

    @Override
    protected void initListener() {

    }
}
