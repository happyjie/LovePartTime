package com.lib.llj.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lib.llj.utils.FDUnitUtil;
import com.llj.commonlib.R;

/**
 * Created by llj on 2017/12/11.
 */

public class KeyValueItemView extends RelativeLayout {
    private Context context;
    private TextView tvKey, tvValue;
    private String key, value;

    public KeyValueItemView(Context context) {
        super(context);
        initView(context, null);
    }

    public KeyValueItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public KeyValueItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    /**
     * 初始化操作
     *
     * @param context
     */
    private void initView(Context context, AttributeSet attributeSet) {
        this.context = context;
        View.inflate(context, R.layout.layout_key_value_item_view, this);
        tvKey = (TextView) findViewById(R.id.tv_key);
        tvValue = (TextView) findViewById(R.id.tv_value);
        if (attributeSet == null) {
            return;
        }

        TypedArray typeArr = context.obtainStyledAttributes(attributeSet, R.styleable.KeyValueItemView);
        key = typeArr.getString(R.styleable.KeyValueItemView_key);
        value = typeArr.getString(R.styleable.KeyValueItemView_value);

        int keyTextColor = typeArr.getColor(R.styleable.KeyValueItemView_keyTextColor, Color.parseColor("#333333"));
        float keyTextSize = typeArr.getDimension(R.styleable.KeyValueItemView_keyTextSize, FDUnitUtil.dp2px(context, 14));
        int valueTextColor = typeArr.getColor(R.styleable.KeyValueItemView_valueTextColor, Color.parseColor("#666666"));
        float valueTextSize = typeArr.getDimension(R.styleable.KeyValueItemView_valueTextSize, FDUnitUtil.dp2px(context, 14));

        typeArr.recycle();

        tvKey.setText(key);
        tvKey.setTextColor(keyTextColor);
        tvKey.setTextSize(TypedValue.COMPLEX_UNIT_PX, keyTextSize);
        tvValue.setText(value);
        tvValue.setTextColor(valueTextColor);
        tvValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, valueTextSize);
    }


    /**
     * 设置autoLink
     * @param enable
     */
    public void setAutoLink(boolean enable){
        if(enable) {
            tvValue.setAutoLinkMask(Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
        } else {
            tvValue.setAutoLinkMask(0);
        }
    }

    public void setKey(String key){
        this.key = key;
        tvKey.setText(key);
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
        tvValue.setText(value);
    }

    public String getValue() {
        return value;
    }

}
