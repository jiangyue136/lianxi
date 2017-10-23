package com.bc.capital.ui.activity.sub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/6/20.
 * 关注我们页面
 */

public class PhotoActivity extends BaseBussActivity {
    private PhotoView pv_fragmenthome;
    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = PhotoActivity.this;
        setContentView(R.layout.activity_photo);

    }

    @Override
    protected void initView() {
        super.initView();
        pv_fragmenthome = (PhotoView) findViewById(R.id.pv_fragmenthome);
    }
    @Override
    protected void bindEvent() {
        super.bindEvent();
    }
    @Override
    protected void initData() {
        super.initData();
        loadLocalPic();
    }

    private void loadLocalPic() {
        Intent intent=getIntent();
        if(intent !=null)
        {
            byte [] bis=intent.getByteArrayExtra("bitmap");
            Bitmap mBitmap= BitmapFactory.decodeByteArray(bis, 0, bis.length);
            pv_fragmenthome.setImageBitmap(mBitmap);
        }

    }
}
