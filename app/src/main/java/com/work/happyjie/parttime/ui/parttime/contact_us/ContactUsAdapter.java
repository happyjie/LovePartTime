package com.work.happyjie.parttime.ui.parttime.contact_us;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewAdapter;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewHolder;
import com.work.happyjie.parttime.bean.KeyValueItemBean;
import com.work.happyjie.parttime.databinding.ItemPersonInfoBinding;

/**
 * Created by llj on 2018/3/9.
 */

public class ContactUsAdapter extends BaseRecycleViewAdapter<KeyValueItemBean> {
    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_person_info);
    }

    private class ViewHolder extends BaseRecycleViewHolder<KeyValueItemBean, ItemPersonInfoBinding>{

        public ViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void onBindViewHolder(KeyValueItemBean object, int position) {
            mViewBinding.setItem(object);
            mViewBinding.executePendingBindings();
        }
    }
}
