package com.work.happyjie.parttime.ui.parttime.finance;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewAdapter;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewHolder;
import com.work.happyjie.parttime.databinding.ItemFinanceBinding;
import com.work.happyjie.parttime.databinding.ItemIncomeBinding;
import com.work.happyjie.parttime.http.response.GetFinanceInfoResponse;
import com.work.happyjie.parttime.http.response.GetIncomDetailResponse;

/**
 * Created by llj on 2017/12/15.
 */

public class FinanceDetailAdapter extends BaseRecycleViewAdapter<GetFinanceInfoResponse.ListsBean> {

    private Context context;

    public FinanceDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FinanceDetailViewHolder(parent, R.layout.item_finance);
    }

    private class FinanceDetailViewHolder extends BaseRecycleViewHolder<GetFinanceInfoResponse.ListsBean, ItemFinanceBinding> {

        public FinanceDetailViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void onBindViewHolder(GetFinanceInfoResponse.ListsBean object, int position) {
            mViewBinding.setItem(object);
            mViewBinding.tvStatus.setTextColor(context.getResources()
                    .getColor("已通过".equals(object.getStatus()) ? R.color.textColorRed : R.color.color66));
            mViewBinding.executePendingBindings();
        }
    }
}
