package com.zr.wanandroid.module.home.activity;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.github.timedown.PollingCheck;
import com.zr.wanandroid.R;

public class TestService extends IntentService {

    private PollingCheck pollingCheck;

    public TestService() {
        super("name");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        setNotify();
        if (pollingCheck == null) {
            pollingCheck = PollingCheck.get();
        }
        pollingCheck.startForMillis(100, 1000, new PollingCheck.CheckCallback() {
            @Override
            public boolean onCheck(int checkCount) {
                Log.i("=====", "=====" + checkCount);
                if (checkCount == 15) {
                    return true;
                }
                return false;
            }

            @Override
            public void onComplete() {
                //轮询完成
                stopForeground(true);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private static final String TAG = "MyService";
    private static final String ID = "channel_1";
    private static final String NAME = "前台服务";

    private void setNotify() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Log.i("GameUtils", "=====createNotification");
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(false);
            channel.enableVibration(false);
            channel.setVibrationPattern(new long[]{0});
            channel.setSound(null, null);
            manager.createNotificationChannel(channel);

            Notification notification = new Notification.Builder(this, ID)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("正在更新关卡资源")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("正在更新关卡资源")
                    .build();
            startForeground(1, notification);
            /*NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH);

            manager.createNotificationChannel(channel);

            Notification notification = new Notification.Builder(this, ID)
                    .setContentTitle("收到一条重要通知")
                    .setContentText("这是重要通知")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .build();


            startForeground(1, notification);*/

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        PollingCheck.onDestroy(pollingCheck);
    }
}
