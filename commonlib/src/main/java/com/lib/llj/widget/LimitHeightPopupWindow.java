package com.lib.llj.widget;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by wzh on 2017/7/21.<br>
 *    对于7.0 限制高度的PopupWindow
 */

public class LimitHeightPopupWindow extends PopupWindow {

    /**
     * <p>Create a new non focusable popup window which can display the
     * <tt>contentView</tt>. The dimension of the window must be passed to
     * this constructor.</p>
     *
     * <p>The popup does not provide any background. This should be handled
     * by the content view.</p>
     *
     * @param contentView the popup's content
     * @param width the popup's width
     * @param height the popup's height
     */
    public LimitHeightPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height, false);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if(Build.VERSION.SDK_INT >= 24){
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }

        super.showAsDropDown(anchor);
    }
}
