package com.bc.capital.ui.activity.sub;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;

public class SystemActivity extends BaseBussActivity {
    private TextView top_title_tv,tv_currentversion;
    //分别为公告，关于惠恩资本,联系我们,客服,关于我们
    private RelativeLayout rl_activitymain_announcement,rl_activitymain_about,
            rl_activitymain_contact,rl_activitymain_customer,rl_activitymain_me;
    private ImageView app_back;

    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = SystemActivity.this;
        setContentView(R.layout.activity_system);

    }
    @Override
    protected void initView() {
        super.initView();
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        rl_activitymain_announcement = (RelativeLayout) findViewById(R.id.rl_activitymain_announcement);
        rl_activitymain_about = (RelativeLayout) findViewById(R.id.rl_activitymain_about);
        rl_activitymain_contact = (RelativeLayout) findViewById(R.id.rl_activitymain_contact);
        rl_activitymain_customer = (RelativeLayout) findViewById(R.id.rl_activitymain_customer);
        rl_activitymain_me = (RelativeLayout) findViewById(R.id.rl_activitymain_me);
        tv_currentversion = (TextView) findViewById(R.id.tv_currentversion);
        app_back = (ImageView) findViewById(R.id.app_back);

    }
    @Override
    protected void bindEvent() {
        super.bindEvent();
        top_title_tv.setText("系统管理");
        rl_activitymain_announcement.setOnClickListener(mOnClickListener);
        rl_activitymain_about.setOnClickListener(mOnClickListener);
        rl_activitymain_contact.setOnClickListener(mOnClickListener);
        rl_activitymain_customer.setOnClickListener(mOnClickListener);
        rl_activitymain_me.setOnClickListener(mOnClickListener);
        app_back.setOnClickListener(mOnClickListener);
        tv_currentversion.setText(getVersion());
    }
    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_activitymain_announcement:
                    startActivity(AnnouncementActivity.class,null);
                    break;
                case R.id.rl_activitymain_about:
                    startActivity(AboutActivity.class,null);
                    break;
                case R.id.rl_activitymain_contact:
                    startActivity(ContactActivity.class,null);
                    break;
                case R.id.rl_activitymain_customer:
                    contactService();
                    break;
                case R.id.rl_activitymain_me:
                    startActivity(AboutUsActivity.class,null);
                    break;
                case R.id.app_back:
                    onBackPressed();
                    break;

            }
        }
    };
    private void contactService() {
                new AlertDialog.Builder(_context)
                        .setTitle("联系客服")
                        .setMessage("是否现在联系客服：1001011")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //获取手机号码
                                String phone = "1001011";
                                //使用隐式意图，启动系统拨号界面
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + phone));
                                if (ActivityCompat.checkSelfPermission(_context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", null).show();
            }
    /**
     *  * 当前版本号
     *  * @return
     *  
     */
    private String getVersion() {
        String version = "未知版本";
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace(); //如果找不到对应的应用包信息, 就返回"未知版本"
        }
        return version;
    }
}
