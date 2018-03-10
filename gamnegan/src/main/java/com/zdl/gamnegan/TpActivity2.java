package com.zdl.gamnegan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TpActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }


    class MyView extends View {
        Canvas mCanvas;
        Bitmap mBitmap, bmp1, bmp2;
        Paint mPaint;
        int flag = 0;
        float movex, movey, lastx, lasty, initx, inity, initx2, inity2;
        float X_r, Y_r;

        public MyView(Context context) {
            super(context);
            X_r = 0;
            Y_r = 0;
            init();
        }

        public void init() {
            mBitmap = Bitmap.createBitmap(240, 300, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            bmp1 = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_launcher_round);
            bmp2 = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_launcher_round);
            mPaint = new Paint();
            mPaint.setColor(0xFFFFFFFF);

            drawImage(bmp2, 50, 0);
            drawImage(bmp1, 150, 0);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(0xa1a1a1);
            canvas.drawBitmap(mBitmap, X_r, Y_r, mPaint);
            super.onDraw(canvas);
        }

        List<HashMap> list = new ArrayList<HashMap>();
        HashMap map = null;

        /* 在给定的位置画位图记录好他们的初始化坐标，放入一个集合中 */
        public void drawImage(Bitmap bmp, float x, float y) {

            if (bmp == null)
                return;

            /* 记录好图片坐标 */
            map = new HashMap();
            map.put("x", x);
            map.put("y", y);
            map.put("bmp", bmp);
            list.add(map);
            Log.d("MyView", "drawImage--->" + initx + ":" + inity);
            float x_1 = bmp1.getWidth() / 2;
            float y_1 = bmp1.getHeight() / 2;
            mCanvas.drawBitmap(bmp, x - x_1, y - y_1, mPaint);
            invalidate();
        }

        /* 传入点击的坐标点获得位图 */
        public Bitmap bmpClick(float mx, float my) {
            Bitmap bmpy = null;
            HashMap hashMap = null;
            for (Iterator<HashMap> it = list.iterator(); it.hasNext(); ) {
                hashMap = it.next();
                float dx = Float.parseFloat(hashMap.get("x").toString());// 获取到它的初始化X坐标
                float dy = Float.parseFloat(hashMap.get("y").toString());// 获取到它的初始化Y坐标
                bmpy = (Bitmap) hashMap.get("bmp");// 获取到它的初始化的位图
                Log.i("MyView", "bmpClick---> " + dx + ":" + dy);
                if (mx >= (dx - bmpy.getWidth() / 2) && mx <= (dx + bmpy.getWidth() / 2)
                        && my >= (dy - bmpy.getHeight() / 2) && my <= (dy + bmpy.getHeight() / 2)) {
                    return bmpy;
                }

            }
            return null;

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                // 点击时的(x,y)，记录初始位置
                case MotionEvent.ACTION_DOWN:
                    mDragBitmap = bmpClick(x, y);
                    break;
                // 移动时更新(x,y)，同时更新图片
                case MotionEvent.ACTION_MOVE:
                    if (mDragBitmap != null) {
                        if (mDragBitmap.equals(bmp1)) {
                            movex = bmp1.getWidth() / 2;
                            movey = bmp1.getHeight() / 2;
                            mBitmap = bmp1.copy(Bitmap.Config.ARGB_8888, true);
                            X_r = x - movex;
                            Y_r = y - movey;
                            invalidate();
                        } else {
                            movex = bmp2.getWidth() / 2;
                            movey = bmp2.getHeight() / 2;
                            mBitmap = bmp2.copy(Bitmap.Config.ARGB_8888, true);
                            X_r = x - movex;
                            Y_r = y - movey;
                            invalidate();
                        }
                    }
                    break;

                // 弹起时更新(x,y)，并记录位置
                case MotionEvent.ACTION_UP:

                    if (mDragBitmap != null) {
                        map = new HashMap();
                        map.put("x", x);
                        map.put("y", y);
                        map.put("bmp", mDragBitmap);
                        list.add(map);
                        Log.e("bitmap list", "insert finish : [" + x + "," + y + "]");
                    }
                    mDragBitmap = null;
                    break;
            }
            return true;
        }
    }

    private Bitmap mDragBitmap = null;
}
