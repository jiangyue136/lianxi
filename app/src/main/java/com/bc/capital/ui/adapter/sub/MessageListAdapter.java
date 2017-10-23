package com.bc.capital.ui.adapter.sub;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.bean.MessageListBean;
import com.bc.capital.ui.adapter.base.BaseInfoAdapter;
import com.bc.capital.ui.adapter.base.BaseInfoViewHolder;

import java.util.List;

/**
 * 作者：LiYaHong on 2017/6/19 17:12
 * QQ：129929684
 * 邮箱：1299259684@qq.com
 */

public class MessageListAdapter extends BaseInfoAdapter<MessageListBean> {

    private Context mContext;
    private List<MessageListBean> mList;
    private int resId;
    private CbOnClickListener mCbOnClickListener;

    public MessageListAdapter(Context mContext, List<MessageListBean> mList, int resId) {
        super(mContext,mList,resId);
        this.mContext = mContext;
        this.mList = mList;
        this.resId = resId;
    }

    public void setCbOnClickListener(CbOnClickListener cbOnClickListener){
        this.mCbOnClickListener = cbOnClickListener;
    }

    @Override
    public View dealView(Context context, int resId, List<MessageListBean> list, int position, View convertView, BaseInfoViewHolder hoder) {
        hoder = new ViewHolder(convertView) ;
        MessageListBean bean = (MessageListBean) getItem(position);
        hoder.initData(context,bean,position);
        return convertView;
    }

    public void updateAdapter(List<MessageListBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public interface CbOnClickListener{
        void onClick(boolean isSelected, int position);
    }

    class ViewHolder extends BaseInfoViewHolder<MessageListBean>{
        ImageView iv_icon;
        CheckBox cb_selected;
        TextView tv_desc;

        public ViewHolder(View view) {
            super(view);
        }
        public void initView(View view){
          iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
          cb_selected = (CheckBox) view.findViewById(R.id.cb_selected);
            tv_desc = (TextView) view.findViewById(R.id.tv_desc);
        }
        public void initData(Context context, MessageListBean bean, final int position){
            super.initData(context, bean, position);

            final MessageListBean msgBean = (MessageListBean) getItem(position);
            if (msgBean.getMsgState() == 0) {//说明未读
                iv_icon.setImageResource(R.mipmap.no_read);
            } else {
                iv_icon.setImageResource(R.mipmap.have_read);
            }
            if (msgBean.isShowCb()) {
                cb_selected.setVisibility(View.VISIBLE);
            } else {
                cb_selected.setVisibility(View.GONE);
            }
            cb_selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cb_selected.isChecked()) {
                        msgBean.setSelected(true);
                    } else {
                        msgBean.setSelected(false);
                    }
                    if (mCbOnClickListener != null) {
                        mCbOnClickListener.onClick(msgBean.isSelected(), position);
                    }
                    notifyDataSetChanged();
                }
            });
            if (msgBean.isSelected()) {
                cb_selected.setChecked(true);
            } else {
                cb_selected.setChecked(false);
            }
            tv_desc.setText(msgBean.getMsgDesc());
        }
    }
}
