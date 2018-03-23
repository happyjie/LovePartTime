package com.work.happyjie.parttime.widget.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.work.happyjie.parttime.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyk on 2016/5/23.
 * ExpandTab过滤器
 */
public class ExpandTabFilterView extends RelativeLayout implements ViewBaseAction {
    private ListView mListView;
    private final List<String> items = new ArrayList<String>();
    private final List<String> itemsVaule = new ArrayList<String>();
    private OnSelectListener mOnSelectListener;
    private TextAdapter adapter;
    private String showText = "";

    public String getShowText() {
        return showText;
    }

    public ExpandTabFilterView(Context context, List<String> viewTextList, List<String> viewTextListId) {
        super(context);
        items.addAll(viewTextList);
        itemsVaule.addAll(viewTextListId);
        initView(context);
    }

    /**
     * UI初始化
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_distance, this, true);
        mListView = (ListView) findViewById(R.id.listView);
        adapter = new TextAdapter(context, items, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
        adapter.setTextSize(16);
        adapter.setSelectedPositionNoNotify(0);
        showText = items.get(0);
        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {if (mOnSelectListener != null) {
                    showText = items.get(position);
                    mOnSelectListener.getValue(Integer.valueOf(itemsVaule.get(position)), items.get(position));
                }
            }
        });
    }

    /**
     * 强制设置选中项位置，不通知选中事件
     * @param selectPosition
     */
    public void setSelectedItemNotNotify(int selectPosition){
        if(null != adapter){
            adapter.setSelectedPositionNoNotify(selectPosition);
        }
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        void getValue(int distance, String showText);
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}

