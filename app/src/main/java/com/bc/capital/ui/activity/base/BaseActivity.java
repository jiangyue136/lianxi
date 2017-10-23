package com.bc.capital.ui.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/14.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Activity _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(savedInstanceState);
        initView();
        bindEvent();
        initData();
    }

    /**
     * setLayout设置布局
     */
    protected abstract void setLayout(Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定监听事件
     */
    protected abstract void bindEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            doActivityResult(requestCode, data);
        }
    }
    protected void doActivityResult(int requestCode, Intent intent) {
    }

}
