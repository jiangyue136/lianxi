package com.bc.capital.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NoviceActivity extends BaseBussActivity {
    private TextView top_title_tv;
    private ImageView app_back;

    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = NoviceActivity.this;
        setContentView(R.layout.activity_novice);
        isShowToobar(true);
    }
    @Override
    protected void initView() {
        super.initView();
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        app_back = (ImageView) findViewById(R.id.app_back);

    }
    @Override
    protected void bindEvent() {
        super.bindEvent();
        top_title_tv.setText("新手指引");
        app_back.setOnClickListener(mOnClickListener);

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
