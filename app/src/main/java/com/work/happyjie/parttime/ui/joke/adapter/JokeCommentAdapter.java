package com.work.happyjie.parttime.ui.joke.adapter;

import android.view.ViewGroup;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewAdapter;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewHolder;
import com.work.happyjie.parttime.http.response.JokeCommentResult;
import com.work.happyjie.parttime.databinding.ItemJokeCommentBinding;

/**
 * Created by asus on 2018-01-06 .
 */

public class JokeCommentAdapter extends BaseRecycleViewAdapter<JokeCommentResult.DataBean.RecentCommentsBean> {
    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JokeCommentViewHolder(parent, R.layout.item_joke_comment);
    }

    private class JokeCommentViewHolder extends BaseRecycleViewHolder<JokeCommentResult.DataBean.RecentCommentsBean, ItemJokeCommentBinding>{

        public JokeCommentViewHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void onBindViewHolder(JokeCommentResult.DataBean.RecentCommentsBean object, int position) {
            mViewBinding.setItem(object);
            mViewBinding.executePendingBindings();
        }
    }
}
