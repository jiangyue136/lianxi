package com.bc.capital.ui.activity.sub;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.common.MyApplication;
import com.bc.capital.ui.activity.base.BaseBussActivity;

/**
 * Created by Administrator on 2017/7/5.
 */

public class MyAccountActivity extends BaseBussActivity {

    private ImageView app_back;
    private TextView top_title_tv;
    private RelativeLayout gesture_pwd_rl;//手势密码

    private RelativeLayout back_login_rl;//退出登录
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = MyAccountActivity.this;
        setContentView(R.layout.activity_my_account);
    }

    @Override
    protected void initView() {
        super.initView();
        back_login_rl= (RelativeLayout) findViewById(R.id.back_login_rl);
        app_back = (ImageView) findViewById(R.id.app_back);
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        gesture_pwd_rl = (RelativeLayout) findViewById(R.id.gesture_pwd_rl);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        app_back.setOnClickListener(mOnClickListener);
        back_login_rl.setOnClickListener(mOnClickListener);
        gesture_pwd_rl.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {
        super.initData();
        top_title_tv.setText("会员管理");
    }
    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.gesture_pwd_rl:
                    startActivity(GestureActivity.class,null);
                    break;
                case R.id.back_login_rl:
                    backLogin();
                    break;
                case R.id.app_back:
                    onBackPressed();
                    break;
            }
        }
    };
    //给出提出：是否退出登录
    private void backLogin() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定要退出账号吗？")
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearUser();
                        MyApplication.closeAllActivity();
                        startActivity(MainActivity.class, null);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
