package com.work.happyjie.parttime.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.work.happyjie.parttime.R;


/**
 * Created by llj on 2016/5/5.
 */
public abstract class CustomerDialog {

    private Context context;
    private String title = "";
    private int iconDrawableId = -1;
    private String tipContent = "";
    private boolean hasPositiveButton = true;
    private boolean hasNavigationButton = true;
    private String positiveBtnTxt = "确定";
    private String navigationBtnTxt = "取消";
    private AlertDialog mAlertDialog;
    private ImageView ivIcon;
    private boolean canTouchOutside = true;

    public CustomerDialog(Context context) {
        this.context = context;
        this.iconDrawableId = R.mipmap.ic_launcher;
    }

    /**
     * @param context
     * @param iconDrawableId      若不需要顶部图标，只需传入小于0的数值即可
     * @param title
     * @param tipContent
     * @param hasPositiveButton
     * @param hasNavigationButton
     */
    public CustomerDialog(Context context, int iconDrawableId, String title, String tipContent, boolean hasPositiveButton, boolean hasNavigationButton) {
        this.context = context;
        this.iconDrawableId = iconDrawableId;
        this.title = title;
        this.tipContent = tipContent;
        this.hasPositiveButton = hasPositiveButton;
        this.hasNavigationButton = hasNavigationButton;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconDrawableId() {
        return iconDrawableId;
    }

    public void setIconDrawableId(int iconDrawableId) {
        this.iconDrawableId = iconDrawableId;
    }

    public String getTipContent() {
        return tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    public boolean isHasPositiveButton() {
        return hasPositiveButton;
    }

    public void setHasPositiveButton(boolean hasPositiveButton) {
        this.hasPositiveButton = hasPositiveButton;
    }

    public boolean isHasNavigationButton() {
        return hasNavigationButton;
    }

    public void setHasNavigationButton(boolean hasNavigationButton) {
        this.hasNavigationButton = hasNavigationButton;
    }

    public String getPositiveBtnTxt() {
        return positiveBtnTxt;
    }

    public void setPositiveBtnTxt(String positiveBtnTxt) {
        this.positiveBtnTxt = positiveBtnTxt;
    }

    public String getNavigationBtnTxt() {
        return navigationBtnTxt;
    }

    public void setNavigationBtnTxt(String navigationBtnTxt) {
        this.navigationBtnTxt = navigationBtnTxt;
    }

    /**
     * 设置点击外部区域是否取消窗口
     * @param bool
     */
    public void setCanceledOnTouchOutside(boolean bool){
        canTouchOutside = bool;
    }

    public void show() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_customer_dialog, null);
        mAlertDialog = builder.create();
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(view);
        mAlertDialog.setCanceledOnTouchOutside(canTouchOutside);

        ImageView ivTopIcon = (ImageView) view.findViewById(R.id.iv_top_icon);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView btnConfirm = (TextView) view.findViewById(R.id.btn_confirm);
        View btnDivideLine = view.findViewById(R.id.btn_divide_line);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        LinearLayout llBtnArea = (LinearLayout) view.findViewById(R.id.ll_btn_area);

        //顶部Icon
        ivIcon = ivTopIcon;
        if (iconDrawableId <= 0) {
            ivTopIcon.setVisibility(View.GONE);
        } else {
            ivTopIcon.setVisibility(View.VISIBLE);
            if(iconDrawableId > 0)
               ivTopIcon.setImageResource(iconDrawableId);
        }

        //标题
        tvTitle.setText(title);
        tvTitle.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);

        //提示内容
        tvContent.setText(tipContent);

        //按钮
        btnConfirm.setVisibility(hasPositiveButton ? View.VISIBLE : View.GONE);
        btnCancel.setVisibility(hasNavigationButton ? View.VISIBLE : View.GONE);
        btnConfirm.setText(getPositiveBtnTxt());
        btnCancel.setText(getNavigationBtnTxt());
        btnDivideLine.setVisibility(hasNavigationButton ? View.VISIBLE : View.GONE);
        llBtnArea.setVisibility(hasPositiveButton || hasNavigationButton ? View.VISIBLE : View.GONE);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveBtnClickListener();
                mAlertDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationBtnClickListener();
                mAlertDialog.dismiss();
            }
        });

    }

    public abstract void positiveBtnClickListener();
    public abstract void NavigationBtnClickListener();
}
