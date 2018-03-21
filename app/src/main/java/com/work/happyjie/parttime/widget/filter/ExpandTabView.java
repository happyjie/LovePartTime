package com.work.happyjie.parttime.widget.filter;

import android.app.Activity;
import android.content.Context;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lib.llj.widget.LimitHeightPopupWindow;
import com.work.happyjie.parttime.R;

import java.util.ArrayList;

/**
 * 筛选菜单控件
 * 下拉动画，动态生成头部按钮个数
 */

public class ExpandTabView extends LinearLayout implements OnDismissListener {

    private final String TAG = ExpandTabView.class.getSimpleName();
    private View selectedView;
    private ArrayList<String> mTextArray = new ArrayList<String>();
    private ArrayList<RelativeLayout> mViewArray = new ArrayList<RelativeLayout>();
    private ArrayList<TextView> mTextViews = new ArrayList<>();
    private ArrayList<View> mViews = new ArrayList<>();
    private Context mContext;
    private final int SMALL = 0;
    private int displayWidth;
    private int displayHeight;
    private LimitHeightPopupWindow popupWindow;
    private int selectPosition;
    private boolean isExpandTabViewSingleLine = true;

    public ExpandTabView(Context context) {
        super(context);
        init(context);
    }

    public ExpandTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化页面
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * 根据选择的位置设置tabItem显示的值
     */
    public void setTitle(String valueText, int position) {
        if (position < mTextViews.size()) {
            mTextViews.get(position).setText(valueText);
        }
    }

    /**
     * 根据选择的位置设置tabItem显示的值
     */
    public void setTitle(String valueText, float textSize, int position) {
        if (position < mTextViews.size()) {
            mTextViews.get(position).setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            mTextViews.get(position).setText(valueText);
        }
    }

    /**
     * 根据选择的位置设置tabItem显示的值
     */
    public void setTitle(Spanned valueText, int position) {
        if (position < mTextViews.size()) {
            mTextViews.get(position).setText(valueText);
        }
    }

    /**
     * 根据选择的位置获取tabItem显示的值
     */
    public String getTitle(int position) {
        if (position < mTextViews.size() && mTextViews.get(position).getText() != null) {
            return mTextViews.get(position).getText().toString();
        }
        return "";
    }

    /**
     * 设置tabItem的个数和初始值
     */
    public void setValue(ArrayList<String> textArray, ArrayList<View> viewArray) {
        if (mContext == null) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mTextArray = textArray;
        for (int i = 0; i < viewArray.size(); i++) {
            //添加筛选类型视图
            View layout = inflater.inflate(R.layout.expand_tabview_layout, this, false);
            layout.setTag(i);
            addView(layout);
            mViews.add(layout);
            //添加分割线
            LinearLayout line = (LinearLayout) inflater.inflate(R.layout.expand_tab_view_textview, this, false);
            if (i < viewArray.size() - 1) {
                LayoutParams lp = new LayoutParams(2, LayoutParams.FILL_PARENT);
                addView(line, lp);
            }
            //设置筛选类型文本
            TextView textView = (TextView) layout.findViewById(R.id.expand_tabview_tv);
            textView.setSingleLine(isExpandTabViewSingleLine);
            textView.setText(mTextArray.get(i));
            mTextViews.add(textView);

            final RelativeLayout relativeLayout = new RelativeLayout(mContext);
            int maxHeight = (int) (displayHeight * 0.7);
            RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, maxHeight);
            relativeLayout.setTag(SMALL);
            relativeLayout.addView(viewArray.get(i), rl);
            mViewArray.add(relativeLayout);

            relativeLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPressBack();
                }
            });
            relativeLayout.setBackgroundColor(mContext.getResources().getColor(R.color.popup_main_background));
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick()");
                    if (popupWindow != null && popupWindow.isShowing()) {
                        if (selectPosition == (Integer) view.getTag()) {
                            popupWindow.dismiss();
                            return;
                        }
                    }
                    selectedView = view;
                    selectPosition = (Integer) selectedView.getTag();
                    startAnimation();

                    if (popupWindow != null) {
                        Log.i(TAG, "popupWindow.isShowing() =" + popupWindow.isShowing());
                    }
                }
            });
        }
    }

    private void startAnimation() {
        if (popupWindow == null) {
            popupWindow = new LimitHeightPopupWindow(mViewArray.get(selectPosition), displayWidth, displayHeight);
            popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(this);
        }

        if (selectedView.isClickable()) {
            if (!popupWindow.isShowing()) {
                showPopup(selectPosition);
            } else {
                popupWindow.dismiss();
                hideView();
            }
        } else {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
                hideView();
            }
        }

        for (int position = 0; position < mViews.size(); position++) {
            if (position != selectPosition) {
                dismissPopWindowStyle(mViews.get(position));
            }
        }
    }

    /**
     * 显示筛选条件
     *
     * @param position
     */
    private void showPopup(int position) {
        View tView = mViewArray.get(selectPosition).getChildAt(0);
        if (tView instanceof ViewBaseAction) {
            ViewBaseAction f = (ViewBaseAction) tView;
            f.show();
        }
        if (popupWindow.getContentView() != mViewArray.get(position)) {
            popupWindow.setContentView(mViewArray.get(position));
        }
        showPopWindowStyle();
//        popupWindow.showAsDropDown(this, 0, 0);
        popupWindow.showAsDropDown(this);
    }

    /**
     * 设置已选中样式
     */
    private void showPopWindowStyle() {
        if (selectedView != null) {
            TextView textView = (TextView) selectedView.findViewById(R.id.expand_tabview_tv);
            ImageView imageView = (ImageView) selectedView.findViewById(R.id.expand_tabview_img);
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            imageView.setImageResource(R.drawable.ic_arrow_drop_up_gray_24dp);
        }
    }

    private void dismissPopWindowStyleDefault() {
        dismissPopWindowStyle(selectedView);
    }

    /**
     * 设置未选中样式
     *
     * @param selectView
     */
    private void dismissPopWindowStyle(View selectView) {
        if (selectView != null) {
            TextView textView = (TextView) selectView.findViewById(R.id.expand_tabview_tv);
            ImageView imageView = (ImageView) selectView.findViewById(R.id.expand_tabview_img);
            textView.setTextColor(getResources().getColor(R.color.color69));
            imageView.setImageResource(R.drawable.ic_arrow_drop_down_gray_24dp);
        }
    }

    /**
     * 如果菜单成展开状态，则让菜单收回去
     */
    public boolean onPressBack() {
        Log.i(TAG, "onPressBack()");
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            hideView();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置expand_tabview的singleline属性,默认为true
     */
    public void setExpandTabViewSingleLine(boolean isExpandTabViewSingleLine) {
        this.isExpandTabViewSingleLine = isExpandTabViewSingleLine;
    }

    /**
     * 将pop菜单隐藏并改变字体样式
     */
    private void hideView() {
        View tView = mViewArray.get(selectPosition).getChildAt(0);
        if (tView instanceof ViewBaseAction) {
            ViewBaseAction f = (ViewBaseAction) tView;
            f.hide();
        }
        dismissPopWindowStyleDefault();
    }

    @Override
    public void onDismiss() {
        Log.i(TAG, "onDismiss()");
        dismissPopWindowStyleDefault();
//        popupWindow.setOnDismissListener(null);
    }

    /**
     * 获取ExpandTabView过滤器的位置
     *
     * @param tabView
     * @return
     */
    public int getFilterViewPositon(View tabView, ArrayList<View> viewArrayList) {
        for (int i = 0; i < viewArrayList.size(); i++) {
            if (viewArrayList.get(i) == tabView) {
                return i;
            }
        }
        return -1;
    }
}
