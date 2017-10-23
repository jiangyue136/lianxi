package com.bc.capital.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/6/15.
 */

public class BaseRecycleViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseRecycleViewHolder(View view) {
        super(view);
        initView(view);
    }
    public void initView(View view){

    }

    public void initData(Context context, T t, int position){

    }
}
