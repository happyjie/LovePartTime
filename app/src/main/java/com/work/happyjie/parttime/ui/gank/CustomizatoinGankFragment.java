package com.work.happyjie.parttime.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseFragment;
import com.work.happyjie.parttime.databinding.FragmentCustomizationGankBinding;

/**
 * Created by llj on 2017/12/12.
 * 干货定制
 */

public class CustomizatoinGankFragment extends BaseFragment<FragmentCustomizationGankBinding> {
    @Override
    protected int setContentView() {
        return R.layout.fragment_customization_gank;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }
}
