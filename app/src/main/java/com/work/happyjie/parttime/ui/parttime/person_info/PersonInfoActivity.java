package com.work.happyjie.parttime.ui.parttime.person_info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.bean.KeyValueItemBean;
import com.work.happyjie.parttime.databinding.ActivityPersonInfoBinding;

import java.util.ArrayList;
import java.util.List;

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
        setTitle("个人资料");
        showContentView();
        mAdapter = new PersonInfoAdapter();
        mViewBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        mViewBinding.recycleView.setAdapter(mAdapter);

        mAdapter.setDatas(generateTestData());
    }

    @Override
    protected void initListener() {

    }

    private List<KeyValueItemBean> generateTestData(){
        List<KeyValueItemBean> list = new ArrayList<>();
        list.add(new KeyValueItemBean("账号", "A0000001"));
        list.add(new KeyValueItemBean("姓名", "隔壁老王"));
        list.add(new KeyValueItemBean("性别", "男"));
        list.add(new KeyValueItemBean("年龄", "35岁"));
        list.add(new KeyValueItemBean("支付宝", "18888888888"));
        list.add(new KeyValueItemBean("联系方式", "18888888888"));
        list.add(new KeyValueItemBean("身份证", "32323219990101123X"));
        list.add(new KeyValueItemBean("微信", "wx789878"));
        return list;
    }
}
