package com.study.progresslive.TwoProgress;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.study.progresslive.service.ForegroundService;

/**
 * Created by hcw on 2019/3/14.
 * Copyright©hcw.All rights reserved.
 */

public class RemoteServcie extends ForegroundService {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(RemoteServcie.this,LocalService.class),connection, Service.BIND_IMPORTANT);


        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(RemoteServcie.this,LocalService.class));
            bindService(new Intent(RemoteServcie.this,LocalService.class),connection, Service.BIND_IMPORTANT);
        }

    };


}
