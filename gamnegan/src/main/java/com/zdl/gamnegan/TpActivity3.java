package com.zdl.gamnegan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TpActivity3 extends Activity {

    private com.gcssloop.widget.RockerView rocker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpe);
        rocker = findViewById(R.id.rocker);
        if (rocker != null) {
            rocker.setListener(new com.gcssloop.widget.RockerView.RockerListener() {
                @Override
                public void callback(int eventType, int currentAngle, float currentDistance) {
                    switch (eventType) {
                        case RockerView.EVENT_ACTION:
                            // 触摸事件回调
                            Log.e("EVENT_ACTION", "angle=" + currentAngle + " - distance" + currentDistance);

                            if ((45 <= currentAngle) && (currentAngle < 135)) {

                                Log.e("tag", "上");

                            } else if ((135 <= currentAngle) && (currentAngle < 225)) {
                                Log.e("tag", "左");
                            } else if ((225 <= currentAngle) && (currentAngle < 315)) {
                                Log.e("tag", "下");
                            } else {
                                Log.e("tag", "右");
                            }
                            break;
                        case RockerView.EVENT_CLOCK:
                            // 定时回调
                            Log.e("EVENT_CLOCK", "angle=" + currentAngle + " - distance" + currentDistance);
                            break;
                    }
                }
            });

//            rocker.setListener(new RockerView.RockerListener() {
//                @Override
//                public void callback(int eventType, int currentAngle, float currentDistance) {
//                    switch (eventType) {
//                        case RockerView.EVENT_ACTION:
//                            // 触摸事件回调
//                            Log.e("EVENT_ACTION", "angle=" + currentAngle + " - distance" + currentDistance);
//
//                            if ((45 <= currentAngle) && (currentAngle < 135)) {
//
//                                Log.e("tag", "上");
//
//                            } else if ((135 <= currentAngle) && (currentAngle < 225)) {
//                                Log.e("tag", "左");
//                            } else if ((225 <= currentAngle) && (currentAngle < 315)) {
//                                Log.e("tag", "下");
//                            } else {
//                                Log.e("tag", "右");
//                            }
//                            break;
//                        case RockerView.EVENT_CLOCK:
//                            // 定时回调
//                            Log.e("EVENT_CLOCK", "angle=" + currentAngle + " - distance" + currentDistance);
//                            break;
//                    }
//                }
//            });
        }

    }


}
