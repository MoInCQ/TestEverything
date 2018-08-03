package com.mo.testeverything.rocker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mo.testeverything.R;

public class RockerActivity extends AppCompatActivity {

    private final static String TAG = "RockerActivity";



    private kong.qingwei.rockerlibrary.RockerView rockerview_others;
    private TextView tv_log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rocker);

        //rockerView = (RockerView) findViewById(R.id.rockerview);
        //rockerView.setOnNavAndSpeedListener(new RockerView.OnNavAndSpeedListener() {
            //@Override
            //public void onNavAndSpeed(float nav, float speed) {
                //Log.d(TAG, nav + speed + "");
            //}
        //});

        tv_log = (TextView) findViewById(R.id.tv_rocker_log);

        rockerview_others = (kong.qingwei.rockerlibrary.RockerView) findViewById(R.id.rockerview_others);
        rockerview_others.setOnShakeListener(kong.qingwei.rockerlibrary.RockerView.DirectionMode.DIRECTION_8, new kong.qingwei.rockerlibrary.RockerView.OnShakeListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void direction(kong.qingwei.rockerlibrary.RockerView.Direction direction) {
                tv_log.setText("摇动方向 : " + direction);
            }

            @Override
            public void onFinish() {
                tv_log.setText("");
            }
        });

    }
}
