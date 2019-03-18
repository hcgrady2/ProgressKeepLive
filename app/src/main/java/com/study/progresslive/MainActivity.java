package com.study.progresslive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.progresslive.TwoProgress.LocalService;
import com.study.progresslive.TwoProgress.RemoteServcie;
import com.study.progresslive.service.ForegroundService;
import com.study.progresslive.utils.KeepManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过一个像素进行提权
        KeepManager.getInstance().registerKeep(this);


    }


    //前台服务提权
    private void keepLiveTwo(){
        startService(new Intent(this, ForegroundService.class));

    }

    //shuang jin cheng
    private void keepThree(){
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteServcie.class));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unregisterKeep(this);
    }
}
