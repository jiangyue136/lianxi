package com.bc.capital.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;

public class ForgetPwdActivity extends BaseBussActivity {
    private TextView top_title_tv;
    private ImageView app_back;

    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context=ForgetPwdActivity.this;
        setContentView(R.layout.activity_forget_pwd);
    }

    @Override
    protected void initView() {
        super.initView();
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        app_back= (ImageView) findViewById(R.id.app_back);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        app_back.setOnClickListener(mOnClickListener);

    }

    @Override
    protected void initData() {
        super.initData();
        top_title_tv.setText("找回密码");
    }
    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.app_back:
                    onBackPressed();
                    break;
            }
        }
    };
}
