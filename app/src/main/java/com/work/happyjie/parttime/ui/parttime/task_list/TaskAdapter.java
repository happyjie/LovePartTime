package com.work.happyjie.parttime.ui.parttime.task_list;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.lib.llj.utils.ToastUtils;
import com.work.happyjie.parttime.R;
import com.work.happyjie.parttime.application.DailyInformationApplication;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewAdapter;
import com.work.happyjie.parttime.base.BaseAdapter.BaseRecycleViewHolder;
import com.work.happyjie.parttime.databinding.ItemTaskBinding;
import com.work.happyjie.parttime.eventbus.UpdateTaskListEvent;
import com.work.happyjie.parttime.http.RequestCallBack;
import com.work.happyjie.parttime.http.request.ReceiveTaskRequestModel;
import com.work.happyjie.parttime.http.request.ShareTaskRequestModel;
import com.work.happyjie.parttime.http.response.GetTaskListResponse;
import com.work.happyjie.parttime.http.response.base.BaseResponse;
import com.work.happyjie.parttime.tools.share.ShareMsgToWechat;
import com.work.happyjie.parttime.tools.share.ShareTaskHelper;
import com.work.happyjie.parttime.widget.CustomerDialog;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;

/**
 * Created by asus on 2018-01-06 .
 */

public class TaskAdapter extends BaseRecycleViewAdapter<GetTaskListResponse.TaskItem> {
    TaskListFragment fragment;

    private int mCatogary;

    public TaskAdapter(TaskListFragment fragment, int catogary) {
        this.fragment = fragment;
        this.mCatogary = catogary;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskItemViewHolder(parent, R.layout.item_task);
    }

    private class TaskItemViewHolder extends BaseRecycleViewHolder<GetTaskListResponse.TaskItem, ItemTaskBinding> {

        public TaskItemViewHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        protected void onBindViewHolder(GetTaskListResponse.TaskItem object, int position) {
            mViewBinding.setItem(object);
//            mViewBinding.tvFinishedIcon.setVisibility(object.getCount() >= 0
//                    && object.getFinishcount() >= object.getCount() ? View.VISIBLE : View.INVISIBLE);

            if (1 == object.getTasktype()) {  //分享任务
                mViewBinding.tvShareTask.setBackgroundResource(R.drawable.selector_btn_5dp_primary_color);
                mViewBinding.tvShareTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //分享
                        shareTask(object.getTaskid());
//                        shareTaskToWechat(object);
                    }
                });
                mViewBinding.tvReceiveTask.setBackgroundResource(R.drawable.shape_5dp_gray_normal);
                mViewBinding.tvReceiveTask.setOnClickListener(null);

            } else if (3 == object.getTasktype()) {  //线下任务
                mViewBinding.tvShareTask.setBackgroundResource(R.drawable.shape_5dp_gray_normal);
                mViewBinding.tvShareTask.setOnClickListener(null);

                if (1 == mCatogary) {
                    mViewBinding.tvReceiveTask.setBackgroundResource(R.drawable.selector_btn_5dp_primary_color);
                    mViewBinding.tvReceiveTask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //接任务
//                            receiveTask(object.getTaskid());
                            showReceiveTaskCOnfirmDialog(object.getTaskid(), object.getTaskname());
                        }
                    });
                } else {
                    mViewBinding.tvReceiveTask.setBackgroundResource(R.drawable.selector_btn_5dp_gray);
                    mViewBinding.tvReceiveTask.setOnClickListener(null);
                }
            }

            mViewBinding.executePendingBindings();
        }
    }

    /**
     * 分享任务成功后，统计次数
     *
     * @param taskId
     */
    private void shareTask(String taskId) {
        fragment.showLoading();
        ShareTaskRequestModel model = new ShareTaskRequestModel(taskId);
        model.shareTask(new RequestCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse result) {
                fragment.dismissLoading();
                if (null != result) {
                    if (result.isSuccess()) {
                        ToastUtils.showShort("分享记录已成功上传");
                        EventBus.getDefault().post(new UpdateTaskListEvent());
                    } else {
                        ToastUtils.showShort(TextUtils.isEmpty(result.getErrorMsg()) ? "数据异常" : result.getErrorMsg());
                    }
                } else {
                    ToastUtils.showShort("服务器数据异常，请联系管理员处理");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                fragment.dismissLoading();
                ToastUtils.showShort(throwable.getMessage());
            }

            @Override
            public void returnSubscription(Disposable disposable) {
                fragment.addSubscription(disposable);
            }
        });
    }

    /**
     * 接任务
     *
     * @param taskid
     */
    private void receiveTask(String taskid) {
        ReceiveTaskRequestModel model = new ReceiveTaskRequestModel(taskid);
        model.receiveTask(new RequestCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse result) {
                if (null != result) {
                    if (result.isSuccess()) {
                        ToastUtils.showShort("接任务成功");
                        EventBus.getDefault().post(new UpdateTaskListEvent());
                    } else {
                        ToastUtils.showShort(TextUtils.isEmpty(result.getErrorMsg()) ? "服务器异常" : result.getErrorMsg());
                    }
                } else {
                    ToastUtils.showShort("服务器数据异常，请联系管理员处理");
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void returnSubscription(Disposable disposable) {

            }
        });
    }


    private void shareTaskToWechat(GetTaskListResponse.TaskItem task){
        ShareTaskHelper.currentShareTaskId = task.getTaskid();
        if(TextUtils.isEmpty(task.getTasklink())){
            ToastUtils.showShort("缺少待分享的链接");
            return;
        }

        new ShareMsgToWechat(fragment.getActivity())
                .wechatShareWebPage(task.getTasklink(), task.getTaskname(),
                        task.getTaskdesc(), BitmapFactory.decodeResource(
                                DailyInformationApplication.getInstance().getResources(), R.mipmap.ic_launcher), 0);

    }

    /**
     * 接任务二次确认dialog
     * @param taskId
     * @param taskTitle
     */
    private void showReceiveTaskCOnfirmDialog(String taskId, String taskTitle) {
        CustomerDialog dialog = new CustomerDialog(fragment.getActivity(), -1, "确认接受此任务吗", taskTitle, true, true) {
            @Override
            public void positiveBtnClickListener() {
                receiveTask(taskId);
            }

            @Override
            public void NavigationBtnClickListener() {
            }
        };

        dialog.setPositiveBtnTxt("确定");
        dialog.setNavigationBtnTxt("取消");

        dialog.show();
    }
}
