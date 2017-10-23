package com.bc.capital.ui.activity.sub;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;

/**
 * Created by Administrator on 2017/6/27.
 */

public class GestureActivity extends BaseBussActivity {
    private TextView top_title_tv, tv_gesture_reset;
    private ImageView app_back;
    private ToggleButton toggle_more, toggle_fingerprint;
    private SharedPreferences sp;
    private LinearLayout ll_gestuer_fingerprint;
    boolean isOpen;
    private FingerprintManager manager;

    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = GestureActivity.this;
        setContentView(R.layout.activity_gesture);

    }

    @Override
    protected void initView() {
        super.initView();
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        app_back = (ImageView) findViewById(R.id.app_back);
        toggle_more = (ToggleButton) findViewById(R.id.toggle_more);
        sp = this.getSharedPreferences("serect_protect", Context.MODE_PRIVATE);
        tv_gesture_reset = (TextView) findViewById(R.id.tv_gesture_reset);
        ll_gestuer_fingerprint = (LinearLayout) findViewById(R.id.ll_gestuer_fingerprint);
        toggle_fingerprint = (ToggleButton) findViewById(R.id.toggle_fingerprint);
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        top_title_tv.setText("手势管理");
        app_back.setOnClickListener(mOnClickListener);
        tv_gesture_reset.setOnClickListener(mOnClickListener);
        if (getAndroidSDKVersion() >= 23) {

            ll_gestuer_fingerprint.setVisibility(View.VISIBLE);
        } else {
            ll_gestuer_fingerprint.setVisibility(View.GONE);
        }
        toggle_fingerprint.setOnClickListener(mOnClickListener);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.app_back:
                    onBackPressed();
                    break;
                case R.id.tv_gesture_reset:
                    resetGesture();
                    break;
                case R.id.toggle_fingerprint:
                    Boolean b = true;
                    break;
            }
        }
    };

    @Override
    protected void initData() {
        super.initData();
//判断一下，手否开启了手势密码，如果开启啊，先输入手势密码
        SharedPreferences mSharedPreferences = getSharedPreferences("serect_protect", Context.MODE_PRIVATE);
        isOpen = mSharedPreferences.getBoolean("isOpen", false);
        toggle_more.setChecked(isOpen);
        boolean isOpen1 = mSharedPreferences.getBoolean("isOpen_Fingerprint", false);
        toggle_fingerprint.setChecked(isOpen1);
        SetGesturePasssword();
        SetFingerprintPasssword();
    }

    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
            Log.e("TAG", "----------------------" + version);
        } catch (NumberFormatException e) {
            Log.e("TAG", e.toString());
        }
        return version;
    }

    private void resetGesture() {
        boolean checked = toggle_more.isChecked();
        if (checked) {
            startActivity(GestureEditActivity.class, null);
        } else {
            Toast.makeText(_context, "手势密码并未开启", Toast.LENGTH_LONG).show();
        }
    }

    private void SetFingerprintPasssword() {

        toggle_fingerprint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //判断是否有指纹录入
                manager = (FingerprintManager) _context.getSystemService(Context.FINGERPRINT_SERVICE);
                if (ActivityCompat.checkSelfPermission(_context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                if (!manager.hasEnrolledFingerprints()) {
                    Toast.makeText(_context, "没有录入指纹", Toast.LENGTH_SHORT).show();
                    toggle_fingerprint.setChecked(false);
                    sp.edit().putBoolean("isOpen_Fingerprint", false).commit();
                } else {
                    if (b) {
                        Toast.makeText(_context,"开启指纹密码",Toast.LENGTH_LONG).show();
                        sp.edit().putBoolean("isOpen_Fingerprint", true).commit();

                    }else {
                        sp.edit().putBoolean("isOpen_Fingerprint", false).commit();
                    }
                }

            }
        });


    }

    private void SetGesturePasssword() {
        toggle_more.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(_context,"开启手势密码",Toast.LENGTH_LONG).show();
                    sp.edit().putBoolean("isOpen", true).commit();
                    String inputCode = sp.getString("inputCode", "");

                    if (TextUtils.isEmpty(inputCode)) {//biashi之前没有设置过
                        new AlertDialog.Builder(_context)
                                .setTitle("设置手势密码")
                                .setMessage("是否现在设置手势密码")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(_context,"现在设置手势密码",Toast.LENGTH_LONG).show();
                                        sp.edit().putBoolean("isOpen", true).commit();
                                        // toggleMore.setChecked(true);
                                        //开启一个新的Activvity：
                                        startActivity(GestureEditActivity.class, null);
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(_context,"取消了现在设置手势密码",Toast.LENGTH_LONG).show();
                                sp.edit().putBoolean("isOpen", false).commit();
                                toggle_more.setChecked(false);
                            }
                        }).show();
                    } else {
                        Toast.makeText(_context,"开启手势",Toast.LENGTH_LONG).show();
                        sp.edit().putBoolean("isOpen", true).commit();
                        //     toggleMore.setChecked(true);
                    }
                } else {
                    Toast.makeText(_context,"关闭手势密码",Toast.LENGTH_LONG).show();
                    sp.edit().putString("inputCode",null).commit();
                    sp.edit().putBoolean("isOpen", false).commit();

                }
            }
        });
    }
}
