package com.work.happyjie.parttime.ui.douban;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseFragment;
import com.work.happyjie.parttime.databinding.FragmentDoubanBinding;

/**
 * Created by llj on 2017/12/12.
 */

public class DoubanFragment extends BaseFragment<FragmentDoubanBinding> {

    @Override
    protected int setContentView() {
        return R.layout.fragment_douban;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showContentView();
    }
}
