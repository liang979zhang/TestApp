package com.zdl.gamnegan;

/**
 * Created by 89667 on 2017/11/30.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyCircle extends View implements View.OnTouchListener {

    /**
     * 定义手指活动的常量
     */
    public static final int UP_ACTION = 0;
    public static final int DOWN_ACTION = 1;
    public static final int LEFT_ACTION = 2;
    public static final int RIGHT_ACTION = 3;
    Paint paint;
    /**
     * 背景圆圈.大小自己随性定义
     */
    int radius_bg = 200;
    /**
     * 中间圆圈的直径
     */
    int radius_circle = 50;

    int backColor;
    int ballColor;

    public MyCircle(Context context) {
        this(context, null);
    }

    public MyCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获得自定义的属性
        TypedArray ta = getContext().obtainStyledAttributes(attrs,
                R.styleable.GameView);
        backColor = ta.getColor(R.styleable.GameView_backgroundcolor,
                Color.GREEN);
        ballColor = ta.getColor(R.styleable.GameView_touchcolor, Color.RED);
        ta.recycle();
        init();
    }

    // 初始化画笔
    private void init() {
        setOnTouchListener(this);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    // 测量方法,专门正对wap_content.定义的方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpcSzie = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecModel == MeasureSpec.AT_MOST
                & heightSpecModel == MeasureSpec.AT_MOST) {
            widthSpecSize = getMeasuredWidth();
            heightSpcSzie = getMeasuredHeight();
        } else if (widthSpecModel == MeasureSpec.AT_MOST) {
            widthSpecSize = getMeasuredWidth();
        } else if (heightSpecModel == MeasureSpec.AT_MOST) {
            heightSpcSzie = getMeasuredHeight();
        }
        setMeasuredDimension(widthSpecSize, heightSpcSzie);
    }

    /**
     * 绘制的的圆心的位置
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /** 繪製外邊的大圈 */
        drawBg(canvas);
        /** 繪製裡面隨手指移動的圓圈 */
        drawCircle(canvas);
    }

    /**
     * 绘制背景圈
     */

    private void drawBg(Canvas canvas) {
        paint.setColor(backColor);
        if (localX == 0 && localY == 0) {
            localX = getWidth() / 2;
            localY = getHeight() / 2;
        }
        canvas.drawCircle(localX, localY, radius_bg, paint);
    }

    /**
     * 繪製隨手指移動的圆形
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        paint.setColor(ballColor);
        if (Xdown == 0 && Ydown == 0) {
            Xdown = getWidth() / 2;
            Ydown = getHeight() / 2;
        }
        canvas.drawCircle(Xdown, Ydown, radius_circle, paint);
    }

    /**
     * 手指在屏幕的相對位置
     */
    int Xdown, Ydown;
    /**
     * 手指离开时的位置
     */
    int Xup, Yup;
    // 记录第一次按下的位置
    int localX = 0;
    int localY = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        Xdown = (int) event.getX();
        Ydown = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                localX = Xdown;
                localY = Ydown;
                // Log.i("main", "====手指按下" + Xdown + "/" + Ydown);
                break;
            case MotionEvent.ACTION_MOVE:
                moveAction();
                break;
            case MotionEvent.ACTION_UP:
                // 分四种情况
                Xup = (int) event.getX();
                Yup = (int) event.getY();
                int actionMove = -1;
                if (Math.abs(Xup - localX) > Math.abs(Yup - localY)) {
                    Log.i("main", "=====横向运动");
                    if (Xup - localX > 0) { // 向右
                        actionMove = RIGHT_ACTION;
                    } else {// 向左
                        actionMove = LEFT_ACTION;
                    }
                } else {
                    Log.i("main", "=====竖向运动");
                    if (Yup - localY > 0) {// 向下
                        actionMove = DOWN_ACTION;
                    } else {
                        // 向上
                        actionMove = UP_ACTION;
                    }
                }
                listener.backActivon(actionMove);
                Xdown = localX;
                Ydown = localY;
                invalidate();
                break;
        }
        return true;
    }

    private void moveAction() {
        String text = "";
        if (Ydown - (radius_circle / 2) < localY - (radius_bg / 2)) {
            text = "超过顶边的位置了";
            Ydown = localY - (radius_bg / 2) - radius_circle;
        }
        if (Ydown + (radius_circle / 2) > localY + (radius_bg / 2)) {
            text = "超过底边的位置了";
            Ydown = localY + (radius_bg) - radius_circle;
        }
        if (Xdown - (radius_circle / 2) < localX - (radius_bg / 2)) {
            text = "超过左边的位置了";
            Xdown = localX - (radius_bg) + radius_circle;
        }
        if (Xdown + (radius_circle / 2) > localX + (radius_bg / 2)) {
            text = "超过右边的位置了";
            Xdown = localX + (radius_bg) - radius_circle;
        }
        invalidate();
        Log.i("main", text);
    }

    TouchMoveListener listener;

    public void setOnTouchMoveListener(TouchMoveListener listener) {
        this.listener = listener;
    }

    /***
     * 定义手机操控的接口
     *
     */
    public interface TouchMoveListener {

        /***
         * 手指的运动的action
         *
         * @param action
         *            传递上下左右的值
         */
        void backActivon(int action);
    }

}
