package com.study.progresslive.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.study.progresslive.activity.KeepActivity;
import com.study.progresslive.activity.KeepReiver;

import java.lang.ref.WeakReference;

/**
 * Created by hcw on 2019/3/14.
 * Copyright©hcw.All rights reserved.
 */

public class KeepManager {
    //管理启动和关闭

    private static final KeepManager intance = new KeepManager();

    public static KeepManager getInstance(){
        return intance;
    }

    private KeepManager(){

    }

    private KeepReiver keepReiver;

    //注册,jobSchedule
    public void registerKeep(Context context){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        keepReiver = new KeepReiver();
        context.registerReceiver(keepReiver,filter);

    }

    //注销
    public void unregisterKeep(Context context){
        if (null != keepReiver){
            context.unregisterReceiver(keepReiver);
        }
    }

    //通过弱引用
    private WeakReference<Activity> weakReference;


    //开启 activity
    public void startKeep(Context context){
        Intent intent = new Intent(context, KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    //finish，开屏时关掉
    public void finshKeep(){
        if (weakReference.get() != null){
            Activity activity = weakReference.get();
            if (null != null){
                activity.finish();
            }
        }
        weakReference = null;

    }


    public void setWeakReference(KeepActivity activity){
        weakReference = new WeakReference<Activity>(activity);
    }




}
