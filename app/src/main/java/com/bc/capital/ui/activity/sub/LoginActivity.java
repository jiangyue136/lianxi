package com.bc.capital.ui.activity.sub;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.bean.DateBean;
import com.bc.capital.bean.LoginBean;
import com.bc.capital.common.AppNetConfig;
import com.bc.capital.common.MyApplication;
import com.bc.capital.ui.activity.base.BaseBussActivity;
import com.google.gson.Gson;
import com.moneyP2P.commons.normal.Md5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/6/19.
 */

public class LoginActivity extends BaseBussActivity{
    private TextView now_register;//立即注册
    private ImageView login_back;//登录页面返回键
    private TextView forget_pwd_tv;//忘记密码
    private EditText login_user_name_et,login_user_pwd_et;//登录账户、密码信息
    private Button login_btn;//登录按钮
    boolean hindPwd =true;
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = LoginActivity.this;
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void initView() {
        super.initView();
        now_register= (TextView) findViewById(R.id.now_register);
        login_back= (ImageView) findViewById(R.id.login_back);
        forget_pwd_tv= (TextView) findViewById(R.id.forget_pwd_tv);
        login_user_name_et= (EditText) findViewById(R.id.login_user_name_et);
        login_user_pwd_et= (EditText) findViewById(R.id.login_user_pwd_et);

        login_btn= (Button) findViewById(R.id.login_btn);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        //立即注册事件监听
        now_register.setOnClickListener(mOnClickListener);
        login_back.setOnClickListener(mOnClickListener);
        forget_pwd_tv.setOnClickListener(mOnClickListener);
        login_btn.setOnClickListener(mOnClickListener);
        login_user_pwd_et.setOnTouchListener(mOnTouchListener);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    //页面跳转事件
    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.forget_pwd_tv:
                    startActivity(ForgetPwdActivity.class,null);
                    break;
                case R.id.now_register:
                    startActivity(RegisterActivity.class,null);
                    break;
                case R.id.login_btn:
                    String username=login_user_name_et.getText().toString().trim();
                    String pwd=login_user_pwd_et.getText().toString().trim();
                    userLogin(username,pwd);
                    break;
                case R.id.login_back:
                    onBackPressed();
                    break;
            }
        }
    };
    //使用xutils登录请求
    private void userLogin(String username,String pwd){
        if("".equals(username)){
            toast("用户名不能为空！", false);
            return;
        }
        if("".equals(pwd)){
            toast("密码不能为空！", false);
            return;
        }
        RequestParams params = new RequestParams(AppNetConfig.USERLOGIN);
        params.addBodyParameter("userName",username);
        params.addBodyParameter("password", Md5Util.execute(pwd));
        x.http().post(params, new Callback.CacheCallback<String>() {
            //请求成功
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                LoginBean loginBean=gson.fromJson(result,LoginBean.class);
                if(loginBean.getCode()==0){
                    DateBean user = loginBean.getData();
                    saveUser(user);
                    toast(loginBean.getMessage(), false);
                    MyApplication.closeAllActivity();
                    startActivity(MainActivity.class,null);
                }else{
                    toast(loginBean.getMessage(), false);
                }
            }
            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                toast("请求失败！",false);
            }
            //请求取消
            @Override
            public void onCancelled(CancelledException cex) {

            }
            //请求完成
            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
    //密码的显示与隐藏事件
    private View.OnTouchListener mOnTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Drawable drawable = login_user_pwd_et.getCompoundDrawables()[2];
            Drawable left = _context.getResources().getDrawable(R.mipmap.shape_pwd);
            Drawable rightNo = _context.getResources().getDrawable(R.mipmap.look_pwd_no);
            Drawable right = _context.getResources().getDrawable(R.mipmap.look_pwd);

            //如果右边没有图片，不再处理
            if (drawable == null) {
                return false;
            }
            //如果不是按下事件，不再处理
            if (event.getAction() != MotionEvent.ACTION_UP) {
                return false;
            }
            if (event.getX() > login_user_pwd_et.getWidth()-login_user_pwd_et.getPaddingRight()-drawable.getIntrinsicWidth()){
                //设置图片大小，必须设置大小，否则不显示
                left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
                right.setBounds(0, 0, right.getMinimumWidth(), right.getMinimumHeight());
                rightNo.setBounds(0, 0, rightNo.getMinimumWidth(), rightNo.getMinimumHeight());
                if(hindPwd){
                    //将输入的字符由掩码转换为字符串
                    login_user_pwd_et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //动态设置DrawableRight图片
                    login_user_pwd_et.setCompoundDrawables(left,null,rightNo,null);
                    hindPwd=false;
                }else{
                    //可以支持将输入的字符转换，包括清除换行符、转换为掩码
                    login_user_pwd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);                    hindPwd=true;
                    //动态设置DrawableRight图片
                    login_user_pwd_et.setCompoundDrawables(left,null,right,null);
                    hindPwd=true;
                }
                //设置光标位置
                login_user_pwd_et.setSelection(login_user_pwd_et.getText().toString().trim().length());
            }
            return false;
        }
    };
}
