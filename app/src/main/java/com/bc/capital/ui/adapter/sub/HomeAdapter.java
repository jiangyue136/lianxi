package com.bc.capital.ui.adapter.sub;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.bean.MoudleBean;
import com.bc.capital.ui.adapter.base.BaseInfoAdapter;
import com.bc.capital.ui.adapter.base.BaseInfoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class HomeAdapter extends BaseInfoAdapter<MoudleBean>{



    public HomeAdapter(Context context, List<MoudleBean> list, int resId) {
        super(context, list, resId);
    }

    @Override
    public View dealView(Context context, int resId, List<MoudleBean> list, int position, View convertView,BaseInfoViewHolder hoder) {
        hoder = new ViewHoder(convertView) ;
        MoudleBean bean = (MoudleBean) getItem(position);
        hoder.initData(context,bean,position);
        return convertView;
    }
    class ViewHoder extends BaseInfoViewHolder<MoudleBean>{
            private TextView tv_name;
            private ImageView iv_icon;

            public ViewHoder(View view) {
                super(view);
            }

            public void initView(View view){
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
            }
            public void initData(Context context, MoudleBean bean,int position){
                super.initData(context, bean, position);
                List images = new ArrayList<>();
                images.add(0,R.mipmap.invitation);
                images.add(1,R.mipmap.investment);
                images.add(2,R.mipmap.loan);
                images.add(3,R.mipmap.novice);
                images.add(4,R.mipmap.invitation);
               // Log.e("TAG",""+bean.getId());
                iv_icon.setImageResource((Integer) images.get(bean.getId()));

                tv_name.setText(bean.getTitle());
            }
    }
}
