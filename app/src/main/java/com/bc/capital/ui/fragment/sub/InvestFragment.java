package com.bc.capital.ui.fragment.sub;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bc.capital.R;
import com.bc.capital.ui.fragment.base.BaseFragment;

/**
 * Created by leeandy007 on 2017/6/15.
 */

public class InvestFragment extends BaseFragment {
    TabLayout tablayout;
    ViewPager viewpager;

    public InvestFragment() {
    }

    public InvestFragment(Context context, int resId) {
        super(context, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {
    tab();

    }
    private void tab(){

        viewpager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {

            String[] itemName = new String[]{
                    "投资列表", "债券转让"
            };

            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return new InvestmentFragment();
                    case 1:
                        return new BondsFragment();

                }
                return new InvestmentFragment();
            }
            @Override
            public int getCount() {

                return itemName.length;

            }

            @Override
            public CharSequence getPageTitle(int position) {

                return itemName[position];
            }
        });

        tablayout.setupWithViewPager(viewpager);
    }

}
