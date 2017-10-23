package com.bc.capital.ui.activity.sub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.capital.R;
import com.bc.capital.util.GestureContentView;
import com.bc.capital.util.GestureDrawline;


public class GestureVerifyActivity extends Activity implements View.OnClickListener {
    /** 手机号码*/
    public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
    /** 意图 */
    public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
    private RelativeLayout mTopLayout;
    private TextView mTextTitle;
    private TextView mTextCancel;
    private ImageView mImgUserLogo;
    private TextView mTextPhoneNumber;
    private TextView mTextTip;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private TextView mTextForget;
    private TextView mTextOther;
    private String mParamPhoneNumber;
    private long mExitTime = 0;
    private int mParamIntentCode;
    private SharedPreferences mSharedPreferences;
    int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gesture_verify);

        ObtainExtraData();

        setUpViews();

        setUpListeners();
    }

    private void ObtainExtraData() {
        mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
        mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
        mSharedPreferences = this.getSharedPreferences("serect_protect", Context.MODE_PRIVATE);
    }

    private void setUpViews() {
        mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextCancel = (TextView) findViewById(R.id.text_cancel);
        mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
        mTextPhoneNumber = (TextView) findViewById(R.id.text_phone_number);
        mTextTip = (TextView) findViewById(R.id.text_tip);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
        mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
        mTextOther = (TextView) findViewById(R.id.text_other_account);

        String inputCode = mSharedPreferences.getString("inputCode","1235789");
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, inputCode,
                new GestureDrawline.GestureCallBack() {

                    @Override
                    public void onGestureCodeInput(String inputCode) {

                    }

                    @Override
                    public void checkedSuccess() {
                        mGestureContentView.clearDrawlineState(0L);
                        Toast.makeText(GestureVerifyActivity.this, "密码正确",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(GestureVerifyActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                        //GestureVerifyActivity.this.finish();
                    }

                    @Override
                    public void checkedFail() {

                        mGestureContentView.clearDrawlineState(1300L);
                        mTextTip.setVisibility(View.VISIBLE);
                        i++;
                        int i1  = 5 - i;
                        mTextTip.setText("密码错误,剩余次数："+i1);
                        if (i == 5){
                            Toast.makeText(GestureVerifyActivity.this,"五次错误请重新登录",Toast.LENGTH_LONG).show();
                            i=0;
                            Intent intent = new Intent(GestureVerifyActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                    }
                });

        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private void setUpListeners() {
        mTextCancel.setOnClickListener(this);
        mTextForget.setOnClickListener(this);
        mTextOther.setOnClickListener(this);
    }

    private String getProtectedMobile(String phoneNumber) {

        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0,3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7,11));

        return builder.toString();
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.text_cancel:
                this.finish();
                break;

            default:
                break;
        }
    }
}
