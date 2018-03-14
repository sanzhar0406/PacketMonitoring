package com.example.sanzharaubakir.packetmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by sanzharaubakir on 12.03.18.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // How to run notification listner
        //Intent i=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        //startActivity(i);

    }
    public void startServices(View v){
        startService(new Intent(this, ProcessMonitoringService.class));
        startService(new Intent(this, NotificationMonitoringService.class));
    }
    public void stopServices(View v){
        stopService(new Intent(this, ProcessMonitoringService.class));
        stopService(new Intent(this, NotificationMonitoringService.class));
    }
    /*public void startProcessMonitoring(View v) {
        startService(new Intent(this, ProcessMonitoringService.class));
    }

    public void stopProcessMonitoring(View v) {
        stopService(new Intent(this, ProcessMonitoringService.class));
    }
    public void startNotificationMonitoring(View v) {
        startService(new Intent(this, NotificationMonitoringService.class));
    }

    public void stopNotificationMonitoring(View v) {
        stopService(new Intent(this, NotificationMonitoringService.class));
    }*/
}
