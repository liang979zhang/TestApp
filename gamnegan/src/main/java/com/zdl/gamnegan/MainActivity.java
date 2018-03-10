package com.zdl.gamnegan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements MyCircle.TouchMoveListener {
    MyCircle myView;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);
        myView = (MyCircle) findViewById(R.id.mycircle);
        myView.setOnTouchMoveListener(this);
    }

    @Override
    public void backActivon(int action) {
        String desc = "";
        switch (action) {
            case MyCircle.UP_ACTION:
                desc = "向上拖动";
                break;
            case MyCircle.DOWN_ACTION:
                desc = "向下拖动";
                break;
            case MyCircle.LEFT_ACTION:
                desc = "向左拖动";
                break;
            case MyCircle.RIGHT_ACTION:
                desc = "向右拖动";
                break;
            default:
                break;
        }
        textView1.setText(desc);
    }
}
