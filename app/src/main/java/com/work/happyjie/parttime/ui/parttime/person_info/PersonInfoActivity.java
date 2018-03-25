package com.work.happyjie.parttime.ui.parttime.person_info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.lib.llj.utils.SharedPreferencesUtils;
import com.lib.llj.utils.ToastUtils;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.bean.AutoLinkKeyValueInfoBean;
import com.work.happyjie.parttime.bean.AutoLinkKeyValueInfoBean;
import com.work.happyjie.parttime.bean.UserInfo;
import com.work.happyjie.parttime.consts.PreferenceConsts;
import com.work.happyjie.parttime.databinding.ActivityPersonInfoBinding;
import com.work.happyjie.parttime.helper.UserInfoHelper;

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
        setCenterTitleVisible(false);
        showContentView();
        mAdapter = new PersonInfoAdapter();
        mViewBinding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        mViewBinding.recycleView.setAdapter(mAdapter);

        List<AutoLinkKeyValueInfoBean> list = generateData();
        if(null != list && list.size() > 0) {
            mAdapter.setDatas(list);
        }
    }

    @Override
    protected void initListener() {

    }

    private List<AutoLinkKeyValueInfoBean> generateData(){

        UserInfo userInfo = UserInfoHelper.getInstance().getUserInfo();

        if(null == userInfo){
            ToastUtils.showShort("未获取到个人信息，请联系管理员处理");
            return null;
        }

        List<AutoLinkKeyValueInfoBean> list = new ArrayList<>();
        list.add(new AutoLinkKeyValueInfoBean("账号", SharedPreferencesUtils.getString(PreferenceConsts.ACCOUNT)));
        list.add(new AutoLinkKeyValueInfoBean("姓名", userInfo.getName()));
        list.add(new AutoLinkKeyValueInfoBean("性别", userInfo.getSex()));
        list.add(new AutoLinkKeyValueInfoBean("年龄", userInfo.getAge()));
        list.add(new AutoLinkKeyValueInfoBean("支付宝", userInfo.getPaynum()));
        list.add(new AutoLinkKeyValueInfoBean("联系方式", userInfo.getTelephone(), true));
        list.add(new AutoLinkKeyValueInfoBean("身份证", userInfo.getCardnum()));
        list.add(new AutoLinkKeyValueInfoBean("微信", userInfo.getChartnum()));
        return list;
    }

    /*private List<AutoLinkKeyValueInfoBean> generateTestData(){
        List<AutoLinkKeyValueInfoBean> list = new ArrayList<>();
        list.add(new AutoLinkKeyValueInfoBean("账号", "A0000001"));
        list.add(new AutoLinkKeyValueInfoBean("姓名", "隔壁老王"));
        list.add(new AutoLinkKeyValueInfoBean("性别", "男"));
        list.add(new AutoLinkKeyValueInfoBean("年龄", "35岁"));
        list.add(new AutoLinkKeyValueInfoBean("支付宝", "18888888888"));
        list.add(new AutoLinkKeyValueInfoBean("联系方式", "18888888888"));
        list.add(new AutoLinkKeyValueInfoBean("身份证", "32323219990101123X"));
        list.add(new AutoLinkKeyValueInfoBean("微信", "wx789878"));
        return list;
    }*/


}
