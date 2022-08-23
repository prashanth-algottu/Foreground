package com.example.foreground1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForegroundService extends Service {
    private Context context;
    private final int NOTIFICATION_ID=1;
    private final String CHANNEL_ID="100";
    private boolean isDestroyed = false;
    Notification notification;

    public ForegroundService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        startForeground(NOTIFICATION_ID,showNotification("This i context"));
    }

    private Notification showNotification(String this_i_context) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID,"Foreground Notification",
                            NotificationManager.IMPORTANCE_HIGH));
        }
        CharSequence content="chinnnu";
        return new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(content)
                .setOnlyAlertOnce(true)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(context, "Starting service....", Toast.LENGTH_SHORT).show();
        Intervalls intervalls = new Intervalls(this);
        intervalls.execute();
        return super.onStartCommand(intent, flags, startId);
    }

//    private void doTask() {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Handler handler = new Handler(Looper.getMainLooper());
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.pikachu);
//                            mediaPlayer.start();
//                        }
//                    });
//                    Thread.sleep(10000);
//                }catch (Exception e){
//                    Toast.makeText(context, "From Exception", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
