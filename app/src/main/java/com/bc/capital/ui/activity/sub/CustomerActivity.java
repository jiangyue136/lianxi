package com.bc.capital.ui.activity.sub;

import android.os.Bundle;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;


/**
 * Created by Administrator on 2017/6/21.
 */

public class CustomerActivity extends BaseBussActivity {
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = CustomerActivity.this;
        setContentView(R.layout.activity_customer);

    }
    @Override
    protected void initView() {
        super.initView();

    }
}
