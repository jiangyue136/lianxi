package com.bc.capital.ui.adapter.base;

import android.content.Context;
import android.view.View;

/**
 * Created by leeandy007 on 2017/6/15.
 */

public class BaseInfoViewHolder<T> {

    private View view;

    public BaseInfoViewHolder(View view){
        this.view = view;
        initView(view);
    }

    public void initView(View view){

    };

    public void initData(Context context, T t, int postion){

    }

}
