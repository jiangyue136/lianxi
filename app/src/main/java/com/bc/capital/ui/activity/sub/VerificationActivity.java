package com.bc.capital.ui.activity.sub;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.bc.capital.ui.activity.base.BaseBussActivity;

/**
 * Created by Administrator on 2017/6/28.
 */

public class VerificationActivity extends BaseBussActivity {
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = VerificationActivity.this;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
    }

    @Override
    protected void initData() {
        super.initData();
        SharedPreferences sp1 = getSharedPreferences("serect_protect", Context.MODE_PRIVATE);
        Log.e("TAG", "sp1+++" + sp1);
        boolean isOpen1 = sp1.getBoolean("isOpen_Fingerprint", false);
        Log.e("TAG", "isOpen1+++" + isOpen1);
        if (isOpen1) {
            startActivity(FingerprintActivity.class, null);
            finish();
        } else {
            //判断一下，手否开启了手势密码，如果开启啊，先输入手势密码
            SharedPreferences sp = getSharedPreferences("serect_protect", Context.MODE_PRIVATE);
            Log.e("TAG", "sp+++" + sp);
            boolean isOpen = sp.getBoolean("isOpen", false);
            Log.e("TAG", "isOpen+++" + isOpen);
            if (isOpen) {
                startActivity(GestureVerifyActivity.class, null);
                finish();
            } else {
                startActivity(MainActivity.class, null);
                finish();
            }


        }
    }
}
