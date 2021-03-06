package com.work.happyjie.parttime.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.lib.llj.utils.ToastUtils;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseAdapter.OnItemClickListener;
import com.work.happyjie.parttime.base.BaseFragment;
import com.work.happyjie.parttime.http.response.NewsDataResult;
import com.work.happyjie.parttime.cache.UserCacheWrapper;
import com.work.happyjie.parttime.databinding.FragmentClassificationNewsBinding;
import com.lib.llj.helper.RxTools;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.NewsRequestModel;
import com.work.happyjie.parttime.tools.webview.WebViewActivity;
import com.work.happyjie.parttime.ui.news.adapter.NewsAdapter;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by llj on 2017/12/15.
 *
 */
public class ClassificationNewsFragment extends BaseFragment<FragmentClassificationNewsBinding> {

    private final static String PARAM_CLASSIFICATION = "param_classification";
    private final static String PARAM_TITLE = "param_title";

    private SmartRefreshLayout refreshLayout;
    private NewsAdapter mAdapter;
    private int mCurPage = 1;
    private boolean isViewInited = false;
    private boolean isDateInited = false;
    private String mCategory = "";
    private String mTitle = "";

    public static ClassificationNewsFragment getInstance(String classification, String title){
        ClassificationNewsFragment fragment = new ClassificationNewsFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_CLASSIFICATION, classification);
        args.putString(PARAM_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = getArguments().getString(PARAM_CLASSIFICATION);
        mTitle = getArguments().getString(PARAM_TITLE);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_classification_news;
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

        mAdapter = new NewsAdapter(getContext());
        mViewBinding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.recycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener<NewsDataResult.DataBean>() {

            @Override
            public void onClick(NewsDataResult.DataBean resultsBean, int position) {
//                NewsDetailActivity.startAction(getActivity(), resultsBean.getContentBean().getDisplay_url());
                WebViewActivity.startAction(getActivity(), resultsBean.getContentBean().getDisplay_url(), resultsBean.getContentBean().getTitle());
            }
        });

        showCacheData();

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
        NewsRequestModel model = new NewsRequestModel(mCategory);
        model.getData(new RequestCallBack<NewsDataResult>() {
            @Override
            public void onSuccess(NewsDataResult bean) {
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

                if(bean.getData() != null && bean.getData().size() > 0){
                    for(NewsDataResult.DataBean item : bean.getData()){
                        item.setContentBean(new Gson().fromJson(item.getContent(), NewsDataResult.ContentBean.class));
                    }
                }

                RxTools.simpleEvent(getContext(), bean, new RxTools.simpleEventCallBack<NewsDataResult>() {
                    @Override
                    public void doSomething(NewsDataResult result) {
                        UserCacheWrapper.saveNewsCache(getContext(), new String[]{mCategory}, bean);
                    }
                });

                showContentView();
                if (1 == page){
                    mAdapter.addAllToFront(bean.getData());
                } else {
                    mAdapter.addAll(bean.getData());
                }
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

    private void showCacheData(){
        List<NewsDataResult.DataBean> list = UserCacheWrapper.getNewsCache(getContext(), new String[]{mCategory});
        if(null != list && list.size() > 0){
            mAdapter.setDatas(list);
        }
    }
}
