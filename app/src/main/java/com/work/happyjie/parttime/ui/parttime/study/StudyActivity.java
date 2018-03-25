package com.work.happyjie.parttime.ui.parttime.study;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseActivity;
import com.work.happyjie.parttime.databinding.ActivityStudyBinding;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.GetStudyInfoRequestModel;
import com.work.happyjie.parttime.http.response.GetStudyInfoResponse;

import io.reactivex.disposables.Disposable;

/**
 * Created by asus on 2018-03-24 .
 */

public class StudyActivity extends BaseActivity<ActivityStudyBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
    }

    @Override
    protected void initView() {
        setCenterTitle("新手教学");
        showInitLoadingView();
        getData();
    }

    @Override
    protected void initListener() {
    }

    private void getData() {
        GetStudyInfoRequestModel model = new GetStudyInfoRequestModel();
        model.getInfo(new RequestCallBack<GetStudyInfoResponse>() {
            @Override
            public void onSuccess(GetStudyInfoResponse result) {
                if (null == result) {
                    showError();
                    return;
                }

                if(result.isSuccess()){
                    showContentView();
                    mViewBinding.setBean(result);
                    mViewBinding.executePendingBindings();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                showError();
            }

            @Override
            public void returnSubscription(Disposable disposable) {
                addSubscription(disposable);
            }
        });
    }
}
