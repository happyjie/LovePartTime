package com.work.happyjie.parttime.ui.parttime.incom_detail;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewAdapter;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewHolder;
import com.work.happyjie.parttime.databinding.ItemIncomeBinding;
import com.work.happyjie.parttime.databinding.ItemNewsImageBinding;
import com.work.happyjie.parttime.databinding.ItemNewsOneImageBinding;
import com.work.happyjie.parttime.databinding.ItemNewsThreeImageBinding;
import com.work.happyjie.parttime.http.response.GetIncomDetailResponse;
import com.work.happyjie.parttime.http.response.NewsDataResult;

/**
 * Created by llj on 2017/12/15.
 */

public class IncomeDetailAdapter extends BaseRecycleViewAdapter<GetIncomDetailResponse.IncomeItem> {


    private Context context;

    public IncomeDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IncomeDetailViewHolder(parent, R.layout.item_income);
    }

    private class IncomeDetailViewHolder extends BaseRecycleViewHolder<GetIncomDetailResponse.IncomeItem, ItemIncomeBinding> {

        public IncomeDetailViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void onBindViewHolder(GetIncomDetailResponse.IncomeItem object, int position) {
            mViewBinding.setItem(object);
            mViewBinding.executePendingBindings();
        }
    }
}
