package com.example.notificationapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID=0;
    private static final String NOTIFICATION_URL="https://www.google.co.id/";

    private Button notifyButton;
    private Button updateButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notifyButton=(Button)findViewById(R.id.notify_but);
        updateButton=(Button)findViewById(R.id.update_but);
        cancelButton=(Button)findViewById(R.id.cancel_but);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });
    }

    private void cancelNotification() {

    }

    private void updateNotification() {
        Bitmap image= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        Intent notificationIntent =  new Intent(this,MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(
                this,NOTIFICATION_ID,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT
        );

        Intent learnMoreIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_URL));
        PendingIntent learnPendingIntent=PendingIntent.getActivity(this,
                NOTIFICATION_ID,learnMoreIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder=new NotificationCompat.Builder(this)
                .setContentTitle("Content")
                .setContentText("Content Text")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                //how to make it better
                .addAction(R.mipmap.ic_launcher,"LEARN MORE",learnPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(notificationPendingIntent)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(image)
                        .setBigContentTitle("Notification Updated"));

        Notification notification= notifyBuilder.build();
        notificationManager.notify(NOTIFICATION_ID,notification);
    }

    private void sendNotification() {
        Intent notificationIntent =  new Intent(this,MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(
                this,NOTIFICATION_ID,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT
        );

        Intent learnMoreIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_URL));
        PendingIntent learnPendingIntent=PendingIntent.getActivity(this,
                NOTIFICATION_ID,learnMoreIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder=new NotificationCompat.Builder(this)
                .setContentTitle("Content")
                .setContentText("Content Text")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                //how to make it better
                .addAction(R.mipmap.ic_launcher,"LEARN MORE",learnPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(notificationPendingIntent);

        Notification notification= notifyBuilder.build();
        notificationManager.notify(NOTIFICATION_ID,notification);
    }
}
