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

    }
    public void onClickStart(View v) {
        startService(new Intent(this, MainService.class));
    }

    public void onClickStop(View v) {
        stopService(new Intent(this, MainService.class));
    }
}
