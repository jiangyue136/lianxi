package com.bc.capital.ui.activity.sub;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.capital.R;
import com.bc.capital.bean.MessageListBean;
import com.bc.capital.ui.activity.base.BaseBussActivity;
import com.bc.capital.ui.adapter.sub.MessageListAdapter;
import com.bc.capital.util.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */

public class MessageActivity extends BaseBussActivity{
    private ListView lv_message_list;//展示消息列表
    private LinearLayout ll_message_list;//用于隐藏底部
    private TextView tv_cancel, tv_select_all, tv_edit_msg;//取消编辑， 全选， 编辑消息
    private Button btn_delete, btn_have_read;//删除， 已读
    private ImageView iv_back;//返回
    private boolean isDelete = false;//是否处于删除状态
    private List<MessageListBean> mList;
    private MessageListAdapter adapter;
    private boolean isSelectedAll = false;
    private HashMap<Integer, Boolean> saveCheckStateMap = new HashMap<>();//保存用户点击的位置以及复选框状态

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.MESSAGE_LIST_CB_SINGLE_SELECTED:
                    if (saveCheckStateMap.isEmpty()) {
                        setEnabled(false, Color.GRAY);
                    } else {
                        setEnabled(true, Color.BLACK);
                    }
                    break;
                case Constant.MESSAGE_LIST_CB_SELECTED_ALL:
                    saveCheckStateMap.clear();
                    for (int i = 0; i < mList.size(); i++) {
                        MessageListBean msgBean = mList.get(i);
                        saveCheckStateMap.put(i, msgBean.isSelected());
                    }
                    setEnabled(true, Color.BLACK);
                    break;
                case Constant.MESSAGE_LIST_CB_CANCEL_ALL:
                    saveCheckStateMap.clear();
                    setEnabled(false, Color.GRAY);
                    break;
            }
        }
    };
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = MessageActivity.this;
        setContentView(R.layout.activity_message);
    }
    @Override
    protected void initView() {
        super.initView();
        lv_message_list = (ListView) findViewById(R.id.lv_message_list);
        ll_message_list = (LinearLayout) findViewById(R.id.ll_message_list);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_edit_msg = (TextView) findViewById(R.id.tv_edit_msg);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_have_read = (Button) findViewById(R.id.btn_have_read);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_select_all = (TextView) findViewById(R.id.tv_select_all);
    }

    @Override
    protected void initData() {
        super.initData();
        setEnabled(false, Color.GRAY);

        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MessageListBean msgBean;
            if (i % 2 == 0) {//偶数
                msgBean = new MessageListBean(0, false, false,
                        "【注册成功】\t惠恩用户186****091"+i+"注册成功");
            } else {//奇数
                msgBean = new MessageListBean(1, false, false,
                        "【注册成功】\t惠恩用户186****091"+i+"注册成功");
            }
            mList.add(msgBean);
        }

        adapter = new MessageListAdapter(this, mList,
                R.layout.adapter_message_list);
        lv_message_list.setAdapter(adapter);
        lv_message_list.setOnItemClickListener(mOnItemClickListener);
        adapter.setCbOnClickListener(mCbOnClickListener);
    }
    @Override
    protected void bindEvent() {
        iv_back.setOnClickListener(mOnClickListener);
        tv_edit_msg.setOnClickListener(mOnClickListener);
        btn_delete.setOnClickListener(mOnClickListener);
        btn_have_read.setOnClickListener(mOnClickListener);
        tv_cancel.setOnClickListener(mOnClickListener);
        tv_select_all.setOnClickListener(mOnClickListener);
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back:
                    onBackPressed();
                    break;
                case R.id.tv_edit_msg:
                    ll_message_list.setVisibility(View.VISIBLE);
                    tv_edit_msg.setVisibility(View.GONE);
                    tv_select_all.setVisibility(View.VISIBLE);
                    tv_cancel.setVisibility(View.VISIBLE);
                    iv_back.setVisibility(View.GONE);
                    checkState(true, false);
                    isDelete = true;
                    break;
                case R.id.btn_delete:
                    deleteMsgAndHaveReadMsg(true);
                    onBack();
                    if (mList.isEmpty()) {
                        tv_edit_msg.setVisibility(View.GONE);
                    }
                    Toast.makeText(_context, "删除成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_have_read:
                    deleteMsgAndHaveReadMsg(false);
                    checkState(false, false);
                    onBack();
                    Toast.makeText(_context, "编辑成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_cancel:
                    onBack();
                    break;
                case R.id.tv_select_all:
                    if (isSelectedAll) {
                        tv_select_all.setText("全选");
                        isSelectedAll = false;
                        mHandler.sendEmptyMessage(Constant.MESSAGE_LIST_CB_CANCEL_ALL);
                    } else {
                        tv_select_all.setText("取消全选");
                        isSelectedAll = true;
                        mHandler.sendEmptyMessage(Constant.MESSAGE_LIST_CB_SELECTED_ALL);
                    }
                    checkState(true, isSelectedAll);
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener mOnItemClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //进入消息详情页面
                    //TODO
                }
            };

    /**
     * 复选框点击操作
     */
    private MessageListAdapter.CbOnClickListener mCbOnClickListener =
            new MessageListAdapter.CbOnClickListener(){
                @Override
                public void onClick(boolean isSelected, int position) {
                    if (isSelected) {
                        saveCheckStateMap.put(position, isSelected);
                    } else {
                        saveCheckStateMap.remove(position);
                    }
                    mHandler.sendEmptyMessage(Constant.MESSAGE_LIST_CB_SINGLE_SELECTED);
                }
            };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBack();
        }
        return true;
    }

    /**
     * 返回操作
     */
    private void onBack(){
        if (isDelete) {
            ll_message_list.setVisibility(View.GONE);
            tv_edit_msg.setVisibility(View.VISIBLE);
            tv_select_all.setVisibility(View.GONE);
            tv_cancel.setVisibility(View.GONE);
            iv_back.setVisibility(View.VISIBLE);
            checkState(false, false);
            isSelectedAll = false;
            tv_select_all.setText("全选");
            isDelete = false;
            saveCheckStateMap.clear();
            setEnabled(false, Color.GRAY);
        } else {
            onBackPressed();
        }
    }

    /**
     * 复选框状态方法
     * @param isShowCb     是否显示复选框
     * @param isSelected   复选框是否被选中
     */
    private void checkState(boolean isShowCb, boolean isSelected){
        List<MessageListBean> msgList = new ArrayList<>();
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                MessageListBean msgBean = mList.get(i);
                msgBean.setShowCb(isShowCb);
                msgBean.setSelected(isSelected);
                msgList.add(msgBean);
            }
            adapter.updateAdapter(msgList);
        }
    }

    /**
     * 编辑和删除消息
     * @param isDelete  是否删除：true删除  false 编辑消息
     */
    private void deleteMsgAndHaveReadMsg(boolean isDelete){
        for (int i = mList.size() - 1; i >= 0; i--) {
            Boolean isSelected = saveCheckStateMap.get(i);
            if (isSelected != null && isSelected) {
                MessageListBean msgBean = mList.get(i);
                if (isDelete) { // 删除操作
                    mList.remove(i);
                } else {  //编辑操作
                    msgBean.setMsgState(1);
                }
            }
        }
        saveCheckStateMap.clear();
        setEnabled(false, Color.GRAY);
        adapter.updateAdapter(mList);
    }

    private void setEnabled(boolean enabled, int textColor){
        btn_delete.setEnabled(enabled);
        btn_have_read.setEnabled(enabled);
        btn_delete.setTextColor(textColor);
        btn_have_read.setTextColor(textColor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //页面销毁的时候移除handler没有发送和处理的消息，避免内存溢出
        mHandler.removeCallbacksAndMessages(null);
    }
}
