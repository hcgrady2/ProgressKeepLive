package com.study.progresslive.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.study.progresslive.utils.KeepManager;

/**
 * Created by hcw on 2019/3/14.
 * Copyright©hcw.All rights reserved.
 */

/**
 *  1像素保活
 */
public class KeepActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setGravity(Gravity.START|Gravity.TOP);



        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = 1;
        layoutParams.height = 1;
        layoutParams.x = 0;
        layoutParams.y = 0;
        window.setAttributes(layoutParams);

        KeepManager.getInstance().setWeakReference(this);



    }
}
