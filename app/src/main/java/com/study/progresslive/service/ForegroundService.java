package com.study.progresslive.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by hcw on 2019/3/14.
 * Copyright©hcw.All rights reserved.
 */

/**
 * 前台服务保活
 */
public class ForegroundService extends Service {

    //全局 id
    private static final int ServiceId = 1;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //return null ?
        return new LocalBinder();
    }


    private class LocalBinder extends Binder{

    }


    /**
     * 这里考虑版本兼容
     * @param intent
     * @param flags
     * @param startId
     * @return
     */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //前台进程
        if (Build.VERSION.SDK_INT < 18){
            // 4.3
            startForeground(ServiceId,new Notification());
        }else if (Build.VERSION.SDK_INT < 26){
            // 7.0
            //通知栏会显示这个前台服务
            startForeground(ServiceId,new Notification());
            //这里让用户没有感知,删除通知
            startService(new Intent(this,InnerService.class));

        }else {
            // 7.0 之后
            //获取通知管理，为了设置 channel
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //其他参数随意，最后一个是级别，要设置级别低，不让用户知道
            NotificationChannel channel = new NotificationChannel("channel","whatever",NotificationManager.IMPORTANCE_MIN);

            if (null != manager){
                manager.createNotificationChannel(channel);
               // Notification notification = new NOtificati
                Notification notification = new NotificationCompat.Builder(this,"channel").build();

                //开启前台服务,用自己的定义的 notification
                startForeground(ServiceId,notification);

            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public static class InnerService extends Service{

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            //再启动一个服务，因为 id 一样
            startForeground(ServiceId,new Notification());
            stopForeground(true);
            stopSelf();
            //停掉并且关掉,也就是通知消失

            return super.onStartCommand(intent, flags, startId);
        }
    }
}
