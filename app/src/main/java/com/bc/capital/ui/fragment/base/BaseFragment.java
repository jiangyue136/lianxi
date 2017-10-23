package com.bc.capital.ui.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.capital.R;

/**
 * Created by leeandy007 on 2017/6/15.
 */

public abstract class BaseFragment extends Fragment {

    public FragmentCallBack mCallBack;

    /**
     * Activity取Fragment所传递的值时调用的回调接口
     * */
    public interface FragmentCallBack{

        /**
         * 传值到activity中
         * */
        public void setValue(Object... param);

    }

    protected Context context;

    protected int resId;

    public BaseFragment() {
    }

    public BaseFragment(Context context, int resId) {
        this.context = context;
        this.resId = resId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if(view == null){
            view = inflater.inflate(resId, null);
            initView(view, savedInstanceState);
            bindEvent();
            initData();
        }
        return view;
    }

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void bindEvent();

    protected abstract void initData();

    @Override
    public void onAttach(Context context) {
        try {
            mCallBack = (FragmentCallBack) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentCallBack");
        }
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        try {
            mCallBack = (FragmentCallBack) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentCallBack");
        }
        super.onAttach(activity);
    }

    protected void startActivity(Class clazz, Bundle bundle){
        Intent intent = new Intent(context, clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        animNext();
    }

    protected void startActivityForResult(Class clazz, Bundle bundle, int requestCode){
        Intent intent = new Intent(context, clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        animNext();
    }

    /**
     * @Desc 页面跳转动画
     * */

    public void animNext(){
        /**<<<------右入左出*/
        ((Activity)context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * @Desc 页面返回动画
     * */
    public void animBack(){
        /**------>>>左入右出*/
        ((Activity)context).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            doActivityResult(requestCode, data);
        }
    }

    protected void doActivityResult(int requestCode, Intent intent){

    }

}
