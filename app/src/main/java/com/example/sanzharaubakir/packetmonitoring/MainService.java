package com.example.sanzharaubakir.packetmonitoring;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.jaredrummler.android.processes.models.Stat;
import com.jaredrummler.android.processes.models.Statm;

import java.io.IOException;
import java.util.List;

public class MainService extends Service {
    public static final String TAG = "MainActivity";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStart");
        String currentApp = "";
        Boolean runService = true;
        int cnt = 100;
        while (cnt > 0) {
            try{
                List<AndroidAppProcess> processes = AndroidProcesses.getRunningForegroundApps(getApplicationContext());
                if (processes == null || processes.isEmpty()){
                    continue;
                }
                for (AndroidAppProcess process : processes) {
                //AndroidAppProcess process = processes.get(0);
                // Get some information about the process
                    String processName = process.name;

                    Stat stat = process.stat();
                    int pid = stat.getPid();
                    int parentProcessId = stat.ppid();
                    long startTime = stat.stime();
                    int policy = stat.policy();
                    char state = stat.state();

                    Statm statm = process.statm();
                    long totalSizeOfProcess = statm.getSize();
                    long residentSetSize = statm.getResidentSetSize();

                    PackageInfo packageInfo = process.getPackageInfo(getApplicationContext(), 0);
                    String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                    if (appName == null || appName.isEmpty()){
                        continue;
                    }
                    if (process.foreground && !appName.equals("PacketMonitoring") && !currentApp.equals(appName)){
                        cnt--;
                        Log.d(TAG, "Currently in foreground - " + appName);
                        currentApp = appName;
                    }
                }
            }catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return super.onStartCommand(intent, flags, startId);

    }
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
        @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
