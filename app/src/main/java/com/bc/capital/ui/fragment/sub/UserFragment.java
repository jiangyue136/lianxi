package com.bc.capital.ui.fragment.sub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bc.capital.R;
import com.bc.capital.common.MyApplication;
import com.bc.capital.ui.activity.sub.InvestmentRecordActivity;
import com.bc.capital.ui.activity.sub.LoanRecordActivity;
import com.bc.capital.ui.activity.sub.LoginActivity;
import com.bc.capital.ui.activity.sub.MainActivity;
import com.bc.capital.ui.activity.sub.MessageActivity;
import com.bc.capital.ui.activity.sub.MyAccountActivity;
import com.bc.capital.ui.activity.sub.MyLoanActivity;
import com.bc.capital.ui.activity.sub.MyMoneyNumberActivity;
import com.bc.capital.ui.activity.sub.RechargeActivity;
import com.bc.capital.ui.activity.sub.SystemActivity;
import com.bc.capital.ui.activity.sub.ToBeAcceptedActivity;
import com.bc.capital.ui.activity.sub.TransactionRecordActivity;
import com.bc.capital.ui.activity.sub.TransferRecordActivity;
import com.bc.capital.ui.activity.sub.WithdrawActivity;
import com.bc.capital.ui.fragment.base.BaseFragment;

/**
 * Created by leeandy007 on 2017/6/15.
 */

public class UserFragment extends BaseFragment {

    private ImageView my_title_settings, my_title_news;
    private LinearLayout my_account;

    private LinearLayout my_money_number;//总金额
    private Button my_recharge_btn;//充值
    private Button my_withdraw_btn;//提现
    private Button loan_btn;//我要借款
    private TextView to_be_accepted_tv;//待收标的
    private LinearLayout investment_record_lr;//投资记录
    private LinearLayout transfer_record_lr;//转让记录
    private LinearLayout loan_record_lr;//借款记录
    private LinearLayout transaction_record_lr;//交易记录
    private ScrollView user_slv;//用户信息

    public UserFragment() {
    }

    public UserFragment(Context context, int resId) {
        super(context, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        my_title_settings = (ImageView) view.findViewById(R.id.my_title_settings);
        my_title_news = (ImageView) view.findViewById(R.id.my_title_news);
        my_account = (LinearLayout) view.findViewById(R.id.my_account);

        my_recharge_btn = (Button) view.findViewById(R.id.my_recharge_btn);
        my_withdraw_btn = (Button) view.findViewById(R.id.my_withdraw_btn);
        loan_btn = (Button) view.findViewById(R.id.loan_btn);
        to_be_accepted_tv = (TextView) view.findViewById(R.id.to_be_accepted_tv);
        my_money_number = (LinearLayout) view.findViewById(R.id.my_money_number);
        investment_record_lr = (LinearLayout) view.findViewById(R.id.investment_record_lr);
        transfer_record_lr = (LinearLayout) view.findViewById(R.id.transfer_record_lr);
        loan_record_lr = (LinearLayout) view.findViewById(R.id.loan_record_lr);
        transaction_record_lr = (LinearLayout) view.findViewById(R.id.transaction_record_lr);

        user_slv = (ScrollView) view.findViewById(R.id.user_slv);
    }

    @Override
    protected void bindEvent() {
        my_title_settings.setOnClickListener(mOnClickListener);
        my_title_news.setOnClickListener(mOnClickListener);
        my_account.setOnClickListener(mOnClickListener);
        my_recharge_btn.setOnClickListener(mOnClickListener);
        my_withdraw_btn.setOnClickListener(mOnClickListener);
        loan_btn.setOnClickListener(mOnClickListener);
        to_be_accepted_tv.setOnClickListener(mOnClickListener);
        investment_record_lr.setOnClickListener(mOnClickListener);
        transfer_record_lr.setOnClickListener(mOnClickListener);
        loan_record_lr.setOnClickListener(mOnClickListener);
        transaction_record_lr.setOnClickListener(mOnClickListener);
        my_money_number.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {
        //判断用户是否已经登录
        isLogin();
    }

    private void isLogin() {
        //查看本地是否有用户的登录信息
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("userName", "");
        if (TextUtils.isEmpty(name)) {
            //本地没有保存过用户信息,给出提示：登录
            user_slv.setVisibility(View.GONE);
            my_title_settings.setVisibility(View.GONE);
            my_title_news.setVisibility(View.GONE);

            doLogin();
        } else {
            //已经登录过，则直接加载用户的信息并显示
            user_slv.setVisibility(View.VISIBLE);
            my_title_settings.setVisibility(View.VISIBLE);
            my_title_news.setVisibility(View.VISIBLE);
            //doUser();
        }
    }

    private void doLogin() {//给出提出：登录
        new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage("您还有没有登录哦!")
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApplication.closeAllActivity();
                        startActivity(MainActivity.class, null);
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(LoginActivity.class, null);
                    }
                })
                .setCancelable(false)
                .show();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.my_title_settings:
                    startActivity(SystemActivity.class, null);
                    break;
                case R.id.my_title_news:
                    startActivity(MessageActivity.class, null);
                    break;
                case R.id.my_account:
                    startActivity(MyAccountActivity.class, null);
                    break;
                case R.id.my_recharge_btn:
                    startActivity(RechargeActivity.class, null);
                    break;
                case R.id.my_withdraw_btn:
                    startActivity(WithdrawActivity.class, null);
                    break;
                case R.id.loan_btn:
                    startActivity(MyLoanActivity.class, null);
                    break;
                case R.id.to_be_accepted_tv:
                    startActivity(ToBeAcceptedActivity.class, null);
                    break;
                case R.id.investment_record_lr:
                    startActivity(InvestmentRecordActivity.class, null);
                    break;
                case R.id.transfer_record_lr:
                    startActivity(TransferRecordActivity.class, null);
                    break;
                case R.id.loan_record_lr:
                    startActivity(LoanRecordActivity.class, null);
                    break;
                case R.id.transaction_record_lr:
                    startActivity(TransactionRecordActivity.class, null);
                    break;
                case R.id.my_money_number:
                    startActivity(MyMoneyNumberActivity.class, null);
                    break;
            }

        }
    };
}
