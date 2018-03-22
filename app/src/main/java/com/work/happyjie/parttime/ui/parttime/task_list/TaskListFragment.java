package com.work.happyjie.parttime.ui.parttime.task_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.lib.llj.helper.RxTools;
import com.lib.llj.utils.ToastUtils;
import com.scwang.smartrefresh.header.CircleHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseAdapter.OnItemClickListener;
import com.work.happyjie.parttime.base.BaseFragment;
import com.work.happyjie.parttime.cache.UserCacheWrapper;
import com.work.happyjie.parttime.databinding.FragmentClassificationNewsBinding;
import com.work.happyjie.parttime.databinding.FragmentTaskListBinding;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.GetTaskListRequestModel;
import com.work.happyjie.parttime.http.request.NewsRequestModel;
import com.work.happyjie.parttime.http.response.GetTaskListResponse;
import com.work.happyjie.parttime.http.response.NewsDataResult;
import com.work.happyjie.parttime.tools.webview.WebViewActivity;
import com.work.happyjie.parttime.ui.news.adapter.NewsAdapter;

import java.util.List;

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

        mAdapter = new TaskAdapter();
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
        if(isViewInited && !isDateInited){
            initData();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mCurPage++;
                getData(mCurPage);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mCurPage = 1;
                getData(mCurPage);
            }
        });
    }

    private void getData(int page){
        GetTaskListRequestModel model = new GetTaskListRequestModel(mCategory, mCurPage);
        model.getTaskList(new RequestCallBack<GetTaskListResponse>() {
            @Override
            public void onSuccess(GetTaskListResponse bean) {
                showContentView();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();

                if(!bean.isSuccess()){
                    if(1 == page){
                        showError();
                    } else {
                        ToastUtils.showShort("数据异常");
                    }
                    return;
                }

                showContentView();
                if (1 == page){
                    mAdapter.clear();
                }
                mAdapter.addAll(bean.getLists());
            }

            @Override
            public void onError(Throwable throwable) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();

                if(mCurPage > 1) mCurPage--;
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

}
