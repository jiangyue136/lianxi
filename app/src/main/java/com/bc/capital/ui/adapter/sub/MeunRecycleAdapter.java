package com.bc.capital.ui.adapter.sub;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.bean.MeunBean;
import com.bc.capital.ui.adapter.base.BaseRecycleAdapter;
import com.bc.capital.ui.adapter.base.BaseRecycleViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class MeunRecycleAdapter extends BaseRecycleAdapter<MeunBean> {
    public MeunRecycleAdapter(Context context, List<MeunBean> list, int resId) {
        super(context, list, resId);
    }

    @Override
    public BaseRecycleViewHolder initViewHolder(View view) {
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void dealView(Context context, int position, BaseRecycleViewHolder holder) {
        ViewHoder viewHoder = (ViewHoder) holder;
        MeunBean bean = (MeunBean) getItem(position);
        viewHoder.initData(context,bean,position);
    }
    class ViewHoder extends BaseRecycleViewHolder<MeunBean>{

        private TextView tv_name;
        private ImageView iv_icon;

        public ViewHoder(View view) {
            super(view);
        }

        public void initView(View view){
            super.initView(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        }
        public void initData(Context context, MeunBean bean,int position){
            super.initData(context, bean, position);
            tv_name.setText(bean.getName());
            iv_icon.setImageResource(bean.getResIcon());

        }
    }
}
