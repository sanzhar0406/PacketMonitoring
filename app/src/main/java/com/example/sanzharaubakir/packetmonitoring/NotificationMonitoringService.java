package com.example.sanzharaubakir.packetmonitoring;

import android.os.Environment;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sanzharaubakir on 13.03.18.
 */

public class NotificationMonitoringService extends NotificationListenerService {
    private static final String TAG = "NotificationMonitoring";
    private static final long NANOSEC_PER_SEC = 1000l*1000*1000;
    private static final long workTime = 15*60*NANOSEC_PER_SEC; // 15 minutes
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        FileWriter fileWriter = null;
        //String fileName = System.getProperty("user.home")+"/notifications.csv";

        File documentsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        documentsDir.mkdirs();
        File file = new File(documentsDir.getAbsolutePath() + "/notifications.csv");

        try {
            fileWriter = new FileWriter(file);

            long startTime = System.nanoTime();
            while ((System.nanoTime() - startTime) < workTime) {
                String packageName = sbn.getPackageName();
                long postedTime = sbn.getPostTime();
                fileWriter.append("Package: " + packageName);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append("PostedTime: " + postedTime);
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
