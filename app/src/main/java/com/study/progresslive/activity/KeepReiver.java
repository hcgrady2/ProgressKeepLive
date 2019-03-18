package com.study.progresslive.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.study.progresslive.utils.KeepManager;

/**
 * Created by hcw on 2019/3/14.
 * Copyright©hcw.All rights reserved.
 */

public class KeepReiver extends BroadcastReceiver {
    //监听息屏
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action,Intent.ACTION_SCREEN_OFF)){
            KeepManager.getInstance().startKeep(context);
        }else if (TextUtils.equals(action,Intent.ACTION_SCREEN_ON)){
            KeepManager.getInstance().finshKeep();
        }
    }
}
