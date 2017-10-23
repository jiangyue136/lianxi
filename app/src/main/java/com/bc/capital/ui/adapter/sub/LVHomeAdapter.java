package com.bc.capital.ui.adapter.sub;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.bean.NoticesBean;
import com.bc.capital.ui.adapter.base.BaseInfoAdapter;
import com.bc.capital.ui.adapter.base.BaseInfoViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class LVHomeAdapter extends BaseInfoAdapter<NoticesBean> {


    public LVHomeAdapter(Context context, List<NoticesBean> list, int resId) {
        super(context, list, resId);
    }

    @Override
    public View dealView(Context context, int resId, List<NoticesBean> list, int position, View convertView, BaseInfoViewHolder hoder) {
        hoder = new ViewHoder(convertView) ;
        NoticesBean bean = (NoticesBean) getItem(position);
        hoder.initData(context,bean,position);
        return convertView;
    }
    class ViewHoder extends BaseInfoViewHolder<NoticesBean>{
        private TextView tv_name;


        public ViewHoder(View view) {
            super(view);
        }

        public void initView(View view){
            tv_name = (TextView) view.findViewById(R.id.tv_item_home);
        }
        public void initData(Context context, NoticesBean bean,int position){
            super.initData(context, bean, position);
            tv_name.setText(bean.getInvestType()+bean.getTitle());
        }
    }
}
