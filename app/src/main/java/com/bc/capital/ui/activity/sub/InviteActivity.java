package com.bc.capital.ui.activity.sub;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import cn.sharesdk.onekeyshare.OnekeyShare;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/6/16.
 */

public class InviteActivity extends BaseBussActivity {
    private Button btn_invite_Invitation;
    private TextView top_title_tv;
    private ImageView iv_activityinvite_orcode;
    Dialog mCameraDialog;
    private PhotoView pv_fragmenthome;
    String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tencent/MicroMsg/WeiXin/";
    private ImageView app_back;

    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = InviteActivity.this;
        setContentView(R.layout.activity_invite);
        isShowToobar(true);
    }
    @Override
    protected void initView() {
        super.initView();
        btn_invite_Invitation = (Button) findViewById(R.id.btn_invite_Invitation);
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        iv_activityinvite_orcode = (ImageView) findViewById(R.id.iv_activityinvite_orcode);
        pv_fragmenthome = (PhotoView) findViewById(R.id.pv_fragmenthome);
        app_back = (ImageView) findViewById(R.id.app_back);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        btn_invite_Invitation.setOnClickListener(mOnClickListener);
        iv_activityinvite_orcode.setOnLongClickListener(mOnLongClickListener);
        app_back.setOnClickListener(mOnClickListener);

    }
    @Override
    protected void initData() {
        super.initData();
        String moudleTitle = null;
        if (getIntent()!=null) {
            moudleTitle = getIntent().getStringExtra("moudleTitle");
        }
        Log.e("TAG", "======================="+moudleTitle);
        top_title_tv.setText(moudleTitle);

    }
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            switch (view.getId()){
                case R.id.iv_activityinvite_orcode:
                    digLog();
                    break;
            }
            return false;
        }
    };
    private View.OnClickListener  mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_invite_Invitation:
                    showShare();
                    break;
                case R.id.app_back:
                    onBackPressed();
                    break;
            }
        }
    };

public void digLog(){
    mCameraDialog = new Dialog(this, R.style.my_dialog);
    LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
            R.layout.popwindow, null);
    root.findViewById(R.id.btn_open_camera).setOnClickListener(btnlistener);
    root.findViewById(R.id.btn_choose_img).setOnClickListener(btnlistener);
    root.findViewById(R.id.btn_cancel).setOnClickListener(btnlistener);
    mCameraDialog.setContentView(root);
    mCameraDialog.setCanceledOnTouchOutside(true);

    Window dialogWindow = mCameraDialog.getWindow();
    dialogWindow.setGravity(Gravity.BOTTOM);
    dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
    WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
    lp.x = 0; // 新位置X坐标
    lp.y = -20; // 新位置Y坐标
    lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
    root.measure(0, 0);
    lp.height = root.getMeasuredHeight();
    lp.alpha = 5f; // 透明度

    dialogWindow.setAttributes(lp);
    mCameraDialog.show();
}


    private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_open_camera: // 保存图片

                    savePicture();
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;

                case R.id.btn_choose_img:
                    iv_activityinvite_orcode.setDrawingCacheEnabled(true);
                    final Bitmap mBitmap = iv_activityinvite_orcode.getDrawingCache(); // 获取图片
//                    Bitmap bmp=((BitmapDrawable)order_con_pic.getDrawable()).getBitmap();
                    Intent intent=new Intent(_context,PhotoActivity.class);
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte [] bitmapByte =baos.toByteArray();
                    intent.putExtra("bitmap", bitmapByte);
                    startActivity(intent);
                    //跳转至关注我们页面
                    break;
                // 取消
                case R.id.btn_cancel:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;
            }
        }
    };

    public void savePicture() {
        //获取内部存储状态
        String state = Environment.getExternalStorageState();
//如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        //通过UUID生成字符串文件名
        String fileName1 = UUID.randomUUID().toString();
//通过Random()类生成数组命名
        Random random = new Random();
        String fileName2 = String.valueOf(random.nextInt(Integer.MAX_VALUE));
        Calendar now = new GregorianCalendar();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String fileName = simpleDate.format(now.getTime());
        try {
            File file = new File(dir + fileName + ".jpg");
            FileOutputStream out = new FileOutputStream(file);
            iv_activityinvite_orcode.setDrawingCacheEnabled(true);
            final Bitmap mBitmap = iv_activityinvite_orcode.getDrawingCache(); // 获取图片
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            Toast.makeText(_context,"保存成功，图片名为:"+fileName,Toast.LENGTH_LONG).show();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private void showShare() {
            OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();
            // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
            oks.setTitle(getString(R.string.app_name));
            // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
            oks.setTitleUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
            // text是分享文本，所有平台都需要这个字段
            oks.setText("世界上最遥远的距离，是我在if里你在else里，似乎一直相伴又永远分离；\n" +
                    "     世界上最痴心的等待，是我当case你是switch，或许永远都选不上自己；\n" +
                    "     世界上最真情的相依，是你在try我在catch。无论你发神马脾气，我都默默承受，静静处理。到那时，再来期待我们的finally。");
            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
            oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片

            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl("http://www.1367730523@qq.com");
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment("word妈呀，精辟的不要不要的！");
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(getResources().getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl("http://www.1367730523@qq.com");

            // 启动分享GUI
            oks.show(this);


        }

}
