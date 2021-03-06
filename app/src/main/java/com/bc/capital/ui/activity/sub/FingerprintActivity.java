package com.bc.capital.ui.activity.sub;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;
import com.bc.capital.util.FingerprintUtil;

/**
 * Created by Administrator on 2017/6/27.
 */

public class FingerprintActivity extends BaseBussActivity{
    int i = 0;
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = FingerprintActivity.this;
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
        onFingerprintClick();
    }

    public void onFingerprintClick(){

        FingerprintUtil.callFingerPrint(new FingerprintUtil.OnCallBackListenr() {
            AlertDialog dialog;
            @Override
            public void onSupportFailed() {
                showToast("当前设备不支持指纹");
            }

            @Override
            public void onInsecurity() {
                showToast("当前设备未处于安全保护中");
            }

            @Override
            public void onEnrollFailed() {
                showToast("请到设置中设置指纹");
            }

            @Override
            public void onAuthenticationStart() {
                AlertDialog.Builder builder = new AlertDialog.Builder(_context);
                View view = LayoutInflater.from(_context).inflate(R.layout.layout_fingerprint,null);
                initView(view);
                builder.setView(view);
                builder.setCancelable(false);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.removeMessages(0);
                        FingerprintUtil.cancel();
                        onBackPressed();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                showToast(errString.toString());
                if (dialog != null  &&dialog.isShowing()){
                    dialog.dismiss();
                    handler.removeMessages(0);
                }
            }

            @Override
            public void onAuthenticationFailed() {

                i++;
                int i1 = 5 - i;
                showToast("解锁失败,剩余次数："+i1);
                if (i == 5) {
                    Toast.makeText(_context,"五次错误请重新登录",Toast.LENGTH_LONG).show();
                    i=0;
                   startActivity(LoginActivity.class,null);
                    finish();
                }
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                showToast(helpString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                showToast("解锁成功");
                if (dialog != null  &&dialog.isShowing()){
                    dialog.dismiss();
                    handler.removeMessages(0);
                    Intent i = new Intent(FingerprintActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }
    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                int i = postion % 5;
                if (i == 0){
                    tv[4].setBackground(null);
                    tv[i].setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                else{
                    tv[i].setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    tv[i-1].setBackground(null);
                }
                postion++;
                handler.sendEmptyMessageDelayed(0,100);
            }
        }
    };
    TextView[] tv = new TextView[5];
    private int postion = 0;
    private void initView(View view) {
        postion = 0;
        tv[0] = (TextView) view.findViewById(R.id.tv_1);
        tv[1] = (TextView) view.findViewById(R.id.tv_2);
        tv[2] = (TextView) view.findViewById(R.id.tv_3);
        tv[3] = (TextView) view.findViewById(R.id.tv_4);
        tv[4] = (TextView) view.findViewById(R.id.tv_5);
        handler.sendEmptyMessageDelayed(0,100);
    }


    public void showToast(String name ){
        Toast.makeText(FingerprintActivity.this,name,Toast.LENGTH_SHORT).show();
    }
}
