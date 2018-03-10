package com.zdl.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2";

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart2: ");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume:2 ");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause:2 ");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop:2 ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy:2 ");
    }
}
