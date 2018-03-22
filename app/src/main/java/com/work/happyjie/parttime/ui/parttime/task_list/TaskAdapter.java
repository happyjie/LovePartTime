package com.work.happyjie.parttime.ui.parttime.task_list;

import android.view.View;
import android.view.ViewGroup;

import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewAdapter;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewHolder;
import com.work.happyjie.parttime.databinding.ItemJokeCommentBinding;
import com.work.happyjie.parttime.databinding.ItemTaskBinding;
import com.work.happyjie.parttime.http.response.GetTaskListResponse;
import com.work.happyjie.parttime.http.response.JokeCommentResult;

/**
 * Created by asus on 2018-01-06 .
 */

public class TaskAdapter extends BaseRecycleViewAdapter<GetTaskListResponse.TaskItem> {
    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskItemViewHolder(parent, R.layout.item_task);
    }

    private class TaskItemViewHolder extends BaseRecycleViewHolder<GetTaskListResponse.TaskItem, ItemTaskBinding>{

        public TaskItemViewHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void onBindViewHolder(GetTaskListResponse.TaskItem object, int position) {
            mViewBinding.setItem(object);

            if("转发".equals(object.getTasktype())){
                mViewBinding.tvShareTask.setBackgroundResource(R.drawable.selector_btn_5dp_primary_color);
                mViewBinding.tvShareTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //分享
                    }
                });
                mViewBinding.tvReceiveTask.setBackgroundResource(R.drawable.selector_btn_5dp_gray);
                mViewBinding.tvReceiveTask.setOnClickListener(null);

            } else if("线下".equals(object.getTasktype())){
                mViewBinding.tvShareTask.setBackgroundResource(R.drawable.selector_btn_5dp_gray);
                mViewBinding.tvShareTask.setOnClickListener(null);
                mViewBinding.tvReceiveTask.setBackgroundResource(R.drawable.selector_btn_5dp_primary_color);
                mViewBinding.tvReceiveTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //接任务
                    }
                });
            }

            mViewBinding.executePendingBindings();
        }
    }
}
