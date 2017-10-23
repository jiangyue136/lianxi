package com.bc.capital.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewHolder> {
    protected Context context;

    protected List<T> list;

    protected int resId;//布局文件

    public BaseRecycleAdapter( Context context, List<T> list, int resId) {
        this.context = context;
        this.list = list;
        this.resId = resId;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,resId,null);
        return initViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        dealView(context,position,holder);
    }
    public Object getItem(int postion){
        return list.get(postion);
    }

    public abstract BaseRecycleViewHolder initViewHolder(View view);

    public abstract void dealView(Context context, int position, BaseRecycleViewHolder holder);
    @Override
    public int getItemCount() {
        return list.size();
    }
}
