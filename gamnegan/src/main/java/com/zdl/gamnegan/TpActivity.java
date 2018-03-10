package com.zdl.gamnegan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TpActivity extends Activity {
    private ImageView dragImg;

    /**
     * 父控件的宽高
     */
    private int screenWidth;
    private int screenHeight;
    private RelativeLayout rl;
    /**
     * 圆球的宽高
     */
    private int imHeight;
    private int imWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp);
        init();
        initListener();

        initData();
    }

    private void initData() {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        rl.measure(w, h);
        screenHeight = rl.getMeasuredHeight();
        screenWidth = rl.getMeasuredWidth();
        imHeight = dragImg.getMeasuredHeight();
        imWidth = dragImg.getMinimumWidth();


        Log.e("tag", "宽== " + screenWidth + "高==" + screenHeight);
    }

    /**
     * 初始化
     */
    private void init() {
        rl = findViewById(R.id.rl);
        dragImg = (ImageView) this.findViewById(R.id.iv_drag);
//        Display display = getWindowManager().getDefaultDisplay();
//        screenWidth = display.getWidth();
//        screenHeight = display.getHeight();
//        screenWidth = rl.getWidth();//获取屏幕宽度
//        screenHeight = rl.getHeight();//获取屏幕高度
    }

    /**
     * 第一次按下的位置
     */
    private int localX;//起点X
    private int localY;//起点Y

    /**
     * 手指在屏幕的相對位置
     */
    int Xdown, Ydown;
    /**
     * 手指离开时的位置
     */
    int Xup, Yup;

    /**
     * 初始化点击事件
     */
    private void initListener() {
        dragImg.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                Xdown = (int) event.getX();
                Ydown = (int) event.getY();

                switch (action) {
                    case MotionEvent.ACTION_DOWN://按下状态
                        localX = (int) event.getRawX();
                        localY = (int) event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE://拖动状态
                        int dx = (int) event.getRawX() - localX;//拖动时和起点X方向位移差值
                        int dy = (int) event.getRawY() - localY;//拖动时和起点Y方向的位移差值

                        int left = v.getLeft() + dx;//拖动后的图片左边坐标
                        int right = v.getRight() + dx;//拖动后的图片右边边坐标
                        int top = v.getTop() + dy;//拖动后的图片上边坐标
                        int bottom = v.getBottom() + dy;//拖动后的图片下边坐标
                        if (left < 0) {//防止超出屏幕左边
                            left = 0;
                            right = left + v.getWidth();
                        }
                        if (right > screenWidth) {//防止超出屏幕右边
                            right = screenWidth;
                            left = right - v.getWidth();
                        }
                        if (top < 0) {//防止超出屏幕上边
                            top = 0;
                            bottom = top + v.getHeight();
                        }
                        if (bottom > screenHeight) {//防止超出屏幕下边
                            bottom = screenHeight;
                            top = bottom - v.getHeight();
                        }
                        v.layout(left, top, right, bottom);
                        v.postInvalidate();
                        localX = (int) event.getRawX();
                        localY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP://松开状态
                        Xup = (int) event.getX();
                        Yup = (int) event.getY();
                        int actionMove = -1;
                        if (Math.abs(Xup - localX) > Math.abs(Yup - localY)) {
                            Log.i("main", "=====横向运动");
                            if (Xup - localX > 0) { // 向右
                                actionMove = 3;
                            } else {// 向左
                                actionMove = 2;
                            }
                        } else {
                            Log.i("main", "=====竖向运动");
                            if (Yup - localY > 0) {// 向下
                                actionMove = 1;
                            } else {
                                // 向上
                                actionMove = 0;
                            }
                        }
                        dragImg.scrollTo(-10,-10);
                        dragImg.invalidate();

                        break;
                }
                return true;
            }
        });
    }
}
