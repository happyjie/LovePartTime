package com.work.happyjie.parttime.ui.gank.adapter;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewAdapter;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewHolder;
import com.work.happyjie.parttime.bean.GankIoDataResult;
import com.work.happyjie.parttime.databinding.ItemOtakuWelfareBinding;

/**
 * Created by llj on 2017/12/12.
 */

public class OtakuWelfareAdapter extends BaseRecycleViewAdapter<GankIoDataResult.ResultsBean> {

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_otaku_welfare);
    }

    private class ViewHolder extends BaseRecycleViewHolder<GankIoDataResult.ResultsBean, ItemOtakuWelfareBinding>{

        public ViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void onBindViewHolder(GankIoDataResult.ResultsBean object, int position) {
            mViewBinding.setBean(object);
            mViewBinding.executePendingBindings();
//            itemView.setOnClickListener((v) -> {
//                if(clickListener != null){
//                    clickListener.onClick(object, position);
//                }
//            });
        }
    }
}
