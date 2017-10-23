package com.bc.capital.ui.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public abstract class BaseInfoAdapter<T> extends BaseAdapter{
    protected Context context;

    protected List<T> list;

    protected int resId;//布局文件

    public BaseInfoAdapter(Context context, List<T> list, int resId) {
        this.context = context;
        this.list = list;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseInfoViewHolder holder = null;
        if (view == null){
            view = View.inflate(context,resId,null);
            holder = new BaseInfoViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (BaseInfoViewHolder) view.getTag();
        }
        return dealView(context,resId,list,i,view,holder);
    }
    public abstract View dealView(Context context, int resId, List<T> list, int position, View convertView, BaseInfoViewHolder hoder);
}
