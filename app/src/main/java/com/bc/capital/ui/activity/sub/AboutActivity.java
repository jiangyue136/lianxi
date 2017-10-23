package com.bc.capital.ui.activity.sub;

import android.os.Bundle;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;

/**
 * Created by Administrator on 2017/6/21.
 */

public class AboutActivity extends BaseBussActivity {
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = AboutActivity.this;
        setContentView(R.layout.activity_abount);

    }
    @Override
    protected void initView() {
        super.initView();

    }
}
