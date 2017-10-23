package com.bc.capital.ui.activity.sub;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bc.capital.R;
import com.bc.capital.ui.activity.base.BaseBussActivity;
import com.bc.capital.ui.adapter.base.FragmentAdapter;
import com.bc.capital.ui.fragment.base.BaseFragment;
import com.bc.capital.ui.fragment.sub.HomeFragment;
import com.bc.capital.ui.fragment.sub.InvestFragment;
import com.bc.capital.ui.fragment.sub.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseBussActivity implements BaseFragment.FragmentCallBack {
    public ViewPager vp_main;
    private FragmentAdapter fragmentAdapter;
    private Fragment homeFrag, investFrag, userFrag;
    private BottomNavigationView bnv_menu;
    private MenuItem menuItem;
    // 退出时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;

    @Override
    protected void setLayout(Bundle savedInstanceState) {
        super.setLayout(savedInstanceState);
        _context = MainActivity.this;
        background();
        setContentView(R.layout.activity_main);
        isShowToobar(true);

    }

    @Override
    protected void initView() {
        super.initView();
        initToobar(R.mipmap.btn_back_nor, "首页");
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        bnv_menu = (BottomNavigationView) findViewById(R.id.bnv_menu);
    }
    @Override
    protected void bindEvent() {
        super.bindEvent();
        vp_main.addOnPageChangeListener(mOnPageChangeListener);
        bnv_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    protected void initData() {
        super.initData();
        homeFrag = new HomeFragment(_context,R.layout.fragment_home);
        investFrag = new InvestFragment(_context, R.layout.fragment_invest);
        userFrag = new UserFragment(_context, R.layout.fragment_user);
        List<Fragment> list = new ArrayList<>();
        list.add(homeFrag);
        list.add(investFrag);
        list.add(userFrag);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), _context, list);
        vp_main.setAdapter(fragmentAdapter);


    }
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (menuItem != null) {
                menuItem.isChecked();
            }else {
                bnv_menu.getMenu().getItem(0).setChecked(false);
            }
            menuItem = bnv_menu.getMenu().getItem(position);
            menuItem.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.main_home:
                    vp_main.setCurrentItem(0);
                    break;
                case R.id.main_invest:
                    vp_main.setCurrentItem(1);
                    break;
                case R.id.main_user:
                    vp_main.setCurrentItem(2);
                    break;

            }
            return true;
        }
    };
    @Override
    public void setValue(Object... param) {

    }
    private void background(){
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

    }


        //重写onBackPressed()方法,继承自退出的方法
        @Override
        public void onBackPressed() {
            // 判断时间间隔
            if (System.currentTimeMillis()- currentBackPressedTime > BACK_PRESSED_INTERVAL) {
                currentBackPressedTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            } else {
                // 退出
                finish();
        }
    }

}
