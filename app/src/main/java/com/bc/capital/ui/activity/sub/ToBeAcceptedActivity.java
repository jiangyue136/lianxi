package com.bc.capital.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;

public class ToBeAcceptedActivity extends BaseBussActivity{
    private ImageView app_back;
    private TextView top_title_tv;
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context=ToBeAcceptedActivity.this;
        setContentView(R.layout.activity_to_be_accepted);
    }

    @Override
    protected void initView() {
        super.initView();
        app_back = (ImageView) findViewById(R.id.app_back);
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        app_back.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {
        super.initData();
        top_title_tv.setText("待收标的");
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
