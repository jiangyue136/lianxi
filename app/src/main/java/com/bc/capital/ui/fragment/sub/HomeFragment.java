package com.bc.capital.ui.fragment.sub;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.bean.BannersBean;
import com.bc.capital.bean.DataBean;
import com.bc.capital.bean.HomeBean;
import com.bc.capital.bean.InvestBean;
import com.bc.capital.bean.MoudleBean;
import com.bc.capital.bean.NoticesBean;
import com.bc.capital.common.AppNetConfig;
import com.bc.capital.ui.activity.sub.ImageUrlActivity;
import com.bc.capital.ui.activity.sub.InviteActivity;
import com.bc.capital.ui.activity.sub.LoginActivity;
import com.bc.capital.ui.activity.sub.MainActivity;
import com.bc.capital.ui.activity.sub.NoviceActivity;
import com.bc.capital.ui.adapter.sub.HomeAdapter;
import com.bc.capital.ui.adapter.sub.LVHomeAdapter;
import com.bc.capital.ui.fragment.base.BaseFragment;
import com.bc.capital.util.GsonUtil;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by leeandy007 on 2017/6/15.
 */

public class HomeFragment extends BaseFragment {
    private ConvenientBanner convenientBanner;
    private ListView lv_homefragment;
    private HomeAdapter mHomeAdapter;
    private LVHomeAdapter mLVHomeAdapter;
    private GridView gv_meun;
    private TextView tv_home_money,tv_home_people;
    InvestBean invest;
    List<BannersBean> banners;
    List<MoudleBean> moudle;
    List<NoticesBean> notices;
    public HomeFragment() {
    }

    public HomeFragment(Context context, int resId) {
        super(context, resId);
    }
    public Handler mHandler=new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 1:
                    tv_home_money.setText(invest.getAmount());
                    Log.e("TAG", "tv_home_money=========" +invest.getAmount() );

                    tv_home_people.setText(invest.getNumber());
                    break;
                case 2:
                    convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, banners)
                        .setPointViewVisible(true)
                        .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .startTurning(6000)
                        .setManualPageable(true);
                    break;
                case 3:
                    Log.e("TAG","-----------------------------"+moudle);

                    mHomeAdapter  = new HomeAdapter(context, moudle, R.layout.adapter_menu);
                    gv_meun.setAdapter(mHomeAdapter);
                    break;
                case 4:
                    mLVHomeAdapter = new LVHomeAdapter(context, notices, R.layout.item_home);
                    lv_homefragment.setAdapter(mLVHomeAdapter);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        gv_meun = (GridView) view.findViewById(R.id.gv_fragmenthome);
        tv_home_money = (TextView) view.findViewById(R.id.tv_home_money);
        tv_home_people = (TextView) view.findViewById(R.id.tv_home_people);
        lv_homefragment = (ListView) view.findViewById(R.id.lv_homefragment);
    }

    @Override
    protected void bindEvent() {
    }

    @Override
    protected void initData() {

        get();

        gv_meun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long bean = adapterView.getItemIdAtPosition(i);
                switch ((int) bean){
                    case 0:
                        ((MainActivity)getActivity()).vp_main.setCurrentItem(1);
                        //startActivity(FingerprintActivity.class,null);
                        break;
                    case 1:
                        startActivity(LoginActivity.class,null);
                        break;
                    case 2:
                        startActivity(NoviceActivity.class,null);
//                        startActivity(GestureActivity.class,null);

                        break;
                    case 3:
                        String s = moudle.get(3).getTitle();
                        Log.e("TAG","-----------------------==="+s);
                        Bundle bundle = new Bundle();
                        bundle.putString("moudleTitle",s);
                        startActivity(InviteActivity.class,bundle);

                        break;
                }
            }
        });

    }


        public class LocalImageHolderView implements Holder<BannersBean> {

            private ImageView imageView;
            @Override
            public View createView(Context context) {
                View view = View.inflate(context,R.layout.item_viewpager,null);
                imageView = (ImageView) view.findViewById(R.id.iv_view_imageview);
                return view;
            }

            @Override
            public void UpdateUI(Context context, final int position, final BannersBean data) {
                Glide.with(context).load(data.getImageUrl()).dontAnimate().fitCenter().crossFade().into(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s;
                        if (position>=0) {

                            s = banners.get(position).getLinkUrl();
                            if (s!=null){
                                Log.e("TAG","s================="+s);
                                Bundle b  = new Bundle();
                                b.putString("bannersLinkUrl",s);
                                startActivity(ImageUrlActivity.class,b);
                            }
                        }
                    }
                });
            }
    }

    private void get() {


        OkHttpClient okHttpClient = new OkHttpClient();//获取OKhttp客户端对象
        Request build = new Request.Builder()
                .url(AppNetConfig.HOMEURL)
                .build();//创建请求信息
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                HomeBean bean = GsonUtil.getBeanFromJson(json,HomeBean.class);
                onPostExecute(bean);
            }
        });
    }
    private void onPostExecute(HomeBean result) {
        Log.e("TAG", "result=========" + result);

        if (result != null) {
            DataBean mHomeBean = result.getData();
            if (mHomeBean != null) {
                //banner
                banners = mHomeBean.getBanners();
                Log.e("TAG", "banners=========" + banners);
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 2;
                        mHandler.sendMessage(message);
                    }
                });
                thread1.start();
                invest = mHomeBean.getInvest();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                });
                thread.start();
                moudle = mHomeBean.getMoudle();
                Thread thread3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 3;
                        mHandler.sendMessage(message);
                    }
                });
                thread3.start();
                notices = mHomeBean.getNotices();
                Thread thread4 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 4;
                        mHandler.sendMessage(message);
                    }
                });
                thread4.start();
            }
        }
    }
}