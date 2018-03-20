package com.work.happyjie.parttime.ui.parttime.contact_us;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.lib.llj.utils.SharedPreferencesUtils;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.bean.KeyValueItemBean;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.databinding.ActivityPersonInfoBinding;
import com.work.happyjie.parttime.ui.parttime.person_info.PersonInfoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llj on 2018/3/8.
 */

public class ContactUsActivity extends BaseActivity<ActivityPersonInfoBinding> {

    private ContactUsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }

    @Override
    protected void initView() {
        setTitle("个人资料");
        showContentView();
        mAdapter = new ContactUsAdapter();
        mViewBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        mViewBinding.recycleView.setAdapter(mAdapter);

        mAdapter.setDatas(generateData());
    }

    @Override
    protected void initListener() {

    }

    private List<KeyValueItemBean> generateData(){
        List<KeyValueItemBean> list = new ArrayList<>();
        list.add(new KeyValueItemBean("联系方式", SharedPreferencesUtils.getString(PreferenceConsts.CUSTOMER_PHONE)));
        list.add(new KeyValueItemBean("微信", SharedPreferencesUtils.getString(PreferenceConsts.CUSTOMER_WECHAT)));
        list.add(new KeyValueItemBean("QQ", SharedPreferencesUtils.getString(PreferenceConsts.CUSTOMER_QQ)));
        list.add(new KeyValueItemBean("联系地址", SharedPreferencesUtils.getString(PreferenceConsts.COMPANY_ADDRESS)));
        return list;
    }
}
