package com.bc.capital.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2017/6/14.
 */
public class MyApplication extends Application{

    /**
     * 管理activity
     */
    private static List<Activity> list=new ArrayList<>();
    public  static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ShareSDK.initSDK(this);
        x.Ext.init(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                removeActivity(activity);
            }
        });
    }

    public static void addActivity(Activity activity){
        if(activity!=null){
            list.add(activity);
        }
    }

    public static void removeActivity(Activity activity){
        if(activity!=null){
            list.remove(activity);
        }
    }

    public static void closeAllActivity(){
        for (Activity activity:list) {
            activity.finish();
        }
    }

}
