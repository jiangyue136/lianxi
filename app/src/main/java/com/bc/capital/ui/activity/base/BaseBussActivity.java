package com.bc.capital.ui.activity.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.bc.capital.R;
import com.bc.capital.bean.DateBean;

/**
 * Created by Administrator on 2017/6/14.
 */

public class BaseBussActivity extends BaseActivity {

    protected boolean isShow;

    protected  void isShowToobar(boolean isShow){
        this.isShow = isShow;
    }
    @Override
    protected void setLayout(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {

    }
    protected void initToobar(int resId ,String title){
    }
    protected void startActivity(Class clazz , Bundle bundle){
        Intent intent = new Intent(_context,clazz);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        animNext();
    }

    protected void startActivityForResult(Class clazz ,Bundle bundle, int requestCode) {
        Intent intent = new Intent(_context,clazz);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,requestCode);
        animNext();

    }

    /**
     * @Desc 页面跳转动画
     * */

    public void animNext(){
        /**<<<------右入左出*/
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * @Desc 页面返回动画
     * */
    public void animBack(){
        /**------>>>左入右出*/
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void toast(String message, boolean isLengthLong) {
        Toast.makeText(_context, message, isLengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
    //保存用户信息
    public void saveUser(DateBean user){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("userId", user.getSession_user().getUserId());
        editor.putInt("isbank", user.getSession_user().getIsbank());
        editor.putString("userName", user.getSession_user().getUserName());
        editor.putString("realName", user.getSession_user().getRealName());
        editor.putString("realName", user.getSessionId());
        editor.putString("cardId", user.getSession_user().getCardId());
        editor.putString("usr_cust_id", user.getSession_user().getUsrCustId());
        editor.commit();//必须提交，否则保存不成功
    }
    //清除用户数据
    public void clearUser(){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        animBack();
        finish();
    }
}
