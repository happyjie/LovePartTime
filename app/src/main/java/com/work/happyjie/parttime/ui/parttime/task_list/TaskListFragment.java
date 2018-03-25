package com.work.happyjie.parttime.ui.parttime.task_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.lib.llj.utils.ToastUtils;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseFragment;
import com.work.happyjie.parttime.databinding.FragmentTaskListBinding;
import com.work.happyjie.parttime.eventbus.UpdateTaskListEvent;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.GetTaskListRequestModel;
import com.work.happyjie.parttime.http.response.GetTaskListResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.disposables.Disposable;

/**
 * Created by llj on 2017/12/15.
 *
 */
public class TaskListFragment extends BaseFragment<FragmentTaskListBinding> {

    private final static String PARAM_CLASSIFICATION = "param_classification";
    private final static String PARAM_TITLE = "param_title";

    private SmartRefreshLayout refreshLayout;
    private TaskAdapter mAdapter;
    private int mCurPage = 1;
    private boolean isViewInited = false;
    private boolean isDateInited = false;
    private int mCategory = 0;
    private String mTitle = "";

    public static TaskListFragment getInstance(int classification, String title){
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_CLASSIFICATION, classification);
        args.putString(PARAM_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = getArguments().getInt(PARAM_CLASSIFICATION);
        mTitle = getArguments().getString(PARAM_TITLE);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_task_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }

    @Override
    protected void initView() {
        super.initView();

        refreshLayout = mViewBinding.smartRefreshLayout;
        refreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));

        mAdapter = new TaskAdapter(this, mCategory);
        mViewBinding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.recycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((resultsBean, position) -> {
//                NewsDetailActivity.startAction(getActivity(), resultsBean.getContentBean().getDisplay_url());
//                WebViewActivity.startAction(getActivity(), resultsBean.getContentBean().getDisplay_url(), resultsBean.getContentBean().getTitle());
        });

        if(mIsVisible){
            initData();
        }
        isViewInited = true;
    }

    @Override
    protected void onVisible() {
        if(isViewInited && (!isDateInited)){
            initData();
            return;
        }

        if(needUpdateIfNextEnter){
            mCurPage = 1;
            getData();
            needUpdateIfNextEnter = false;
        }
    }

    @Override
    protected void initListener() {
        super.initListener();

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mCurPage++;
                getData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mCurPage = 1;
                getData();
            }
        });
    }

    private void getData(){
        GetTaskListRequestModel model = new GetTaskListRequestModel(mCategory, mCurPage);
        model.getTaskList(new RequestCallBack<GetTaskListResponse>() {
            @Override
            public void onSuccess(GetTaskListResponse bean) {
                showContentView();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();

                if(!bean.isSuccess()){
                    if(1 == mCurPage){
                        showError();
                    } else {
                        mCurPage--;
                        ToastUtils.showShort("数据异常");
                    }
                    return;
                }

                showContentView();
                if (1 == mCurPage){
                    mAdapter.clear();
                } else {
                    if(bean.getLists() != null && 0 == bean.getLists().size()){
                        mCurPage--;
                    }
                }
                mAdapter.addAll(bean.getLists());
            }

            @Override
            public void onError(Throwable throwable) {
                dismissLoading();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();

                if(mCurPage > 1){
                    mCurPage--;
                } else {
                    showError();
                }
            }

            @Override
            public void returnSubscription(Disposable disposable) {
                addSubscription(disposable);
            }
        });
    }

    private void initData(){
        refreshLayout.autoRefresh(0);
        isDateInited = true;
    }

    private boolean needUpdateIfNextEnter = false;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UpdateTaskListEvent messageEvent){
        if(mIsVisible){
            mCurPage = 1;
            getData();
        } else {
            needUpdateIfNextEnter = true;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
