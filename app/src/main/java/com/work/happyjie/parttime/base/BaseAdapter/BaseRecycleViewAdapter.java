package com.work.happyjie.parttime.base.BaseAdapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llj on 2017/12/12.
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewHolder> {

    private List<T> datas = new ArrayList<>();
    protected OnItemClickListener<T> clickListener;
    protected OnItemLongClickListener<T> longClickListener;

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        if(null != clickListener){
            holder.setOnItemClickListener(clickListener);
        }
        if(null != longClickListener){
            holder.setOnItemLongClickListener(longClickListener);
        }
        holder.bindData(datas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }

    public List<T> getDatas() {
        return datas;
    }

    /**
     * 先清空，再装载数据
     * @param list
     */
    public void setDatas(List<T> list){
        datas.clear();
        addAll(list);
    }

    /**
     * 在List最前面添加数据集合
     * @param list
     */
    public void addAllToFront(List<T> list){
        datas.addAll(0, list);
        notifyDataSetChanged();
    }

    /**
     * 在List末尾添加数据集合
     * @param list
     */
    public void addAll(List<T> list){
        datas.addAll(list);
        notifyDataSetChanged();
    }

    public void add(T t){
        datas.add(t);
        notifyDataSetChanged();
    }

    public void remove(int position){
        datas.remove(position);
        notifyDataSetChanged();
    }

    public void remove(T t){
        datas.remove(t);
        notifyDataSetChanged();
    }

    public boolean isEmpty(){
        return null == datas || 0 == datas.size();
    }

    /**
     * 清空数据，清完未添加刷新界面操作，如果需要，可手动调用notifyDataSetChanged()
     */
    public void clear(){
        datas.clear();
    }

    /**
     * 添加数据到指定的位置
     * @param t
     * @param position
     */
    public void add(T t, int position){
        if(position >= getItemCount()){
            datas.add(getItemCount(), t);
        } else {
            datas.add(position, t);
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener){
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> longClickListener) {
        this.longClickListener = longClickListener;
    }
}
