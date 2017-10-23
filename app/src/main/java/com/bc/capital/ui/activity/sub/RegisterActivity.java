package com.bc.capital.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bc.capital.R;
import com.bc.capital.common.AppNetConfig;
import com.bc.capital.ui.activity.base.BaseBussActivity;
import com.bc.capital.util.VerificationUtils;
import com.moneyP2P.commons.normal.Md5Util;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class RegisterActivity extends BaseBussActivity {
    private TextView top_title_tv;
    private ImageView app_back;
    private Button register_btn;//注册按钮
    private Button register_get_verification_code_btn;//注册获取验证码按钮
    private EditText register_phone_et;//输入的手机号
    private EditText register_verification_code_et;//注册验证码
    private EditText register_pwd_et;//注册密码
    private EditText register_invite_code_et;//邀请人邀请码
    private String phone;
    private String verificationCode;
    private String pwd;
    private String inviteCode;
    private String sessionId;


    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = RegisterActivity.this;
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        super.initView();
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        app_back = (ImageView) findViewById(R.id.app_back);
        register_btn = (Button) findViewById(R.id.register_btn);
        register_get_verification_code_btn = (Button) findViewById(R.id.register_get_verification_code_btn);
        register_phone_et = (EditText) findViewById(R.id.register_phone_et);
        register_verification_code_et = (EditText) findViewById(R.id.register_verification_code_et);
        register_pwd_et = (EditText) findViewById(R.id.register_pwd_et);
        register_invite_code_et = (EditText) findViewById(R.id.register_invite_code_et);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        register_btn.setOnClickListener(mOnClickListener);
        register_get_verification_code_btn.setOnClickListener(mOnClickListener);
        app_back.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {
        super.initData();
        top_title_tv.setText("注册");
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.register_get_verification_code_btn:
                    //获取用户注册的手机号
                    phone = register_phone_et.getText().toString().trim();
                    getVerificationCode(phone);
                    break;
                case R.id.register_btn:
                    //获取用户注册信息
                     phone = register_phone_et.getText().toString().trim();
                    verificationCode = register_verification_code_et.getText().toString().trim();
                    pwd = register_pwd_et.getText().toString().trim();
                    inviteCode = register_invite_code_et.getText().toString().trim();
                    submitRegisterUser(phone, verificationCode, pwd, inviteCode,sessionId);
                    break;
                case R.id.app_back:
                    onBackPressed();
                    break;
            }
        }
    };

    //使用xutils网络请求短信验证码
    private void getVerificationCode(String phone) {
        if ("".equals(phone)) {
            toast("手机号码不能为空！", false);
            return;
        }
        if (VerificationUtils.isMobileNO(phone)) {
            //AppNetConfig.GETVERIFICATIONCODE+phone  为拼接好的请求地址
            RequestParams params = new RequestParams(AppNetConfig.GETVERIFICATIONCODE + phone);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                //请求成功
                public void onSuccess(String result) {
                    JSONObject jsonObject = JSON.parseObject(result);
                    Integer code = jsonObject.getInteger("code");
                    if (code == 0) {
                        String data = jsonObject.getString("data");
                        JSONObject jsonObject1 = JSON.parseObject(data);
                        sessionId=jsonObject1.getString("sessionId");
                        toast(jsonObject.getString("message"), false);
                    } else {
                        toast(jsonObject.getString("message"), false);
                    }
                }

                @Override
                //请求失败
                public void onError(Throwable ex, boolean isOnCallback) {
                    toast("联网请求失败!", false);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                //请求完成
                public void onFinished() {
                }
            });

        } else {
            toast("请输入正确的手机号", false);
        }
    }

    //使用xutils提交注册信息
    private void submitRegisterUser(String phone, String verificationCode, String pwd, String inviteCode,String sessionId) {
        if ("".equals(phone)) {
            toast("手机号码不能为空！", false);
            return;
        }
        if (!VerificationUtils.isMobileNO(phone)) {
            toast("请输入正确的手机号", false);
            return;
        }
        if ("".equals(verificationCode)) {
            toast("请输入收到的短信验证码！", false);
            return;
        }
        if ("".equals(pwd)||!(5<=pwd.length())||!(pwd.length()<=20)) {
            toast("密码不能为空,并且是6~20位！", false);
            return;
        }

        RequestParams params = new RequestParams(AppNetConfig.USERREGISTER);
        params.addBodyParameter("phone",phone);
        params.addBodyParameter("code",verificationCode);
        params.addBodyParameter("pwd", Md5Util.execute(pwd));
        params.addBodyParameter("ycode",inviteCode);
        params.addBodyParameter("sessionId",sessionId);
        x.http().post(params, new Callback.CacheCallback<String>() {
            //请求成功
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSON.parseObject(result);
                Integer code = jsonObject.getInteger("code");
                if (code == 0) {
                    toast(jsonObject.getString("message"), false);
                } else {
                    toast(jsonObject.getString("message"), false);
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
                onBackPressed();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
