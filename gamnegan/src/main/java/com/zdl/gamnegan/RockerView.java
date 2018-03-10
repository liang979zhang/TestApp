package com.zdl.gamnegan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.gcssloop.view.utils.DensityUtils;
import com.gcssloop.view.utils.MathUtils;

/**
 * Created by 89667 on 2017/12/1.
 */

public class RockerView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private static int DEFAULT_AREA_RADIUS = 75;
    private static int DEFAULT_ROCKER_RADIUS = 25;
    private static int DEFAULT_AREA_COLOR = -16711681;
    private static int DEFAULT_ROCKER_COLOR = -65536;
    private static int DEFAULT_REFRESH_CYCLE = 30;
    private static int DEFAULT_CALLBACK_CYCLE = 100;
    private SurfaceHolder mHolder;
    private static Thread mDrawThread;
    private static Thread mCallbackThread;
    private static boolean mDrawOk = true;
    private static boolean mCallbackOk = true;
    private Paint mPaint;
    private Point mAreaPosition;
    private Point mRockerPosition;
    private int mAreaRadius;
    private int mRockerRadius;
    private int mAreaColor;
    private int mRockerColor;
    private Bitmap mAreaBitmap;
    private Bitmap mRockerBitmap;
    private RockerListener mListener;
    public static final int EVENT_ACTION = 1;
    public static final int EVENT_CLOCK = 2;
    private int mRefreshCycle;
    private int mCallbackCycle;

    public RockerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RockerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RockerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mAreaRadius = -1;
        this.mRockerRadius = -1;
        this.mRefreshCycle = DEFAULT_REFRESH_CYCLE;
        this.mCallbackCycle = DEFAULT_CALLBACK_CYCLE;
        this.initAttrs(context, attrs);
        this.setPaint();
        if (!this.isInEditMode()) {
            this.configSurfaceView();
            this.configSurfaceHolder();
        }
        this.setZOrderOnTop(true);
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        DEFAULT_AREA_RADIUS = DensityUtils.dip2px(context, 75.0F);
        DEFAULT_ROCKER_RADIUS = DensityUtils.dip2px(context, 25.0F);
        TypedArray ta = context.obtainStyledAttributes(attrs, com.gcssloop.rocker.R.styleable.viewsupport);
        this.mAreaRadius = ta.getDimensionPixelOffset(com.gcssloop.rocker.R.styleable.viewsupport_area_radius, DEFAULT_AREA_RADIUS);
        this.mRockerRadius = ta.getDimensionPixelOffset(com.gcssloop.rocker.R.styleable.viewsupport_rocker_radius, DEFAULT_ROCKER_RADIUS);
        this.mRefreshCycle = ta.getInteger(com.gcssloop.rocker.R.styleable.viewsupport_refresh_cycle, DEFAULT_REFRESH_CYCLE);
        this.mCallbackCycle = ta.getInteger(com.gcssloop.rocker.R.styleable.viewsupport_callback_cycle, DEFAULT_CALLBACK_CYCLE);
        Drawable area_bg = ta.getDrawable(com.gcssloop.rocker.R.styleable.viewsupport_area_background);
        Drawable rocker_bg = ta.getDrawable(com.gcssloop.rocker.R.styleable.viewsupport_rocker_background);
        if (area_bg instanceof BitmapDrawable) {
            this.mAreaBitmap = ((BitmapDrawable) area_bg).getBitmap();
        } else if (area_bg instanceof ColorDrawable) {
            this.mAreaBitmap = null;
            this.mAreaColor = ((ColorDrawable) area_bg).getColor();
        } else {
            this.mAreaBitmap = null;
            this.mAreaColor = DEFAULT_AREA_COLOR;
        }

        if (rocker_bg instanceof BitmapDrawable) {
            this.mRockerBitmap = ((BitmapDrawable) rocker_bg).getBitmap();
        } else if (rocker_bg instanceof ColorDrawable) {
            this.mRockerBitmap = null;
            this.mRockerColor = ((ColorDrawable) rocker_bg).getColor();
        } else {
            this.mRockerBitmap = null;
            this.mRockerColor = DEFAULT_ROCKER_COLOR;
        }

    }

    private void setPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
    }

    private void configSurfaceView() {
        this.setKeepScreenOn(true);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setZOrderOnTop(true);
    }

    private void configSurfaceHolder() {
        this.mHolder = this.getHolder();
        this.mHolder.addCallback(this);
        this.mHolder.setFormat(-2);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int measureWidth = false;
//        int measureHeight = false;
        int defaultWidth = (this.mAreaRadius + this.mRockerRadius) * 2;
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int measureWidth;
        if (widthmode != -2147483648 && widthmode != 0 && widthsize >= 0) {
            measureWidth = widthsize;
        } else {
            measureWidth = defaultWidth;
        }

        int measureHeight;
        if (heightmode != -2147483648 && heightmode != 0 && heightsize >= 0) {
            measureHeight = heightsize;
        } else {
            measureHeight = defaultWidth;
        }

        this.setMeasuredDimension(measureWidth, measureHeight);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mAreaPosition = new Point(w / 2, h / 2);
        this.mRockerPosition = new Point(this.mAreaPosition);
        int tempRadius = Math.min(w - this.getPaddingLeft() - this.getPaddingRight(), h - this.getPaddingTop() - this.getPaddingBottom());
        tempRadius /= 2;
        if (this.mAreaRadius == -1) {
            this.mAreaRadius = (int) ((double) tempRadius * 0.75D);
        }

        if (this.mRockerRadius == -1) {
            this.mRockerRadius = (int) ((double) tempRadius * 0.25D);
        }

    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mDrawThread = new Thread(this);
            mDrawThread.start();
            mCallbackThread = new Thread(new Runnable() {
                public void run() {
                    while (RockerView.mCallbackOk) {
                        RockerView.this.listenerCallback();

                        try {
                            Thread.sleep((long) RockerView.this.mCallbackCycle);
                        } catch (Exception var2) {
                            var2.printStackTrace();
                        }
                    }

                }
            });
            mCallbackThread.start();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawOk = false;
        mCallbackOk = false;
    }

    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == 0) {
            mDrawOk = true;
            mCallbackOk = true;
        } else {
            mDrawOk = false;
            mCallbackOk = false;
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        try {
            int len = MathUtils.getDistance((float) this.mAreaPosition.x, (float) this.mAreaPosition.y, event.getX(), event.getY());
            if (event.getAction() == 0 && len > this.mAreaRadius) {
                return true;
            }

            if (event.getAction() == 2) {
                if (len <= this.mAreaRadius) {
                    this.mRockerPosition.set((int) event.getX(), (int) event.getY());
                } else {
                    this.mRockerPosition = MathUtils.getPointByCutLength(this.mAreaPosition, new Point((int) event.getX(), (int) event.getY()), this.mAreaRadius);
                }

                if (this.mListener != null) {
                    float radian = MathUtils.getRadian(this.mAreaPosition, new Point((int) event.getX(), (int) event.getY()));
                    int angle = this.getAngleConvert(radian);
                    float distance = (float) MathUtils.getDistance((float) this.mAreaPosition.x, (float) this.mAreaPosition.y, event.getX(), event.getY());
                    this.mListener.callback(1, angle, distance);
                }
            }

            if (event.getAction() == 1) {
                this.mRockerPosition = new Point(this.mAreaPosition);
                if (this.mListener != null) {
                    this.mListener.callback(1, -1, 0.0F);
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return true;
    }

    public void run() {
        if (!this.isInEditMode()) {
            Canvas canvas = null;

            while (mDrawOk) {
                try {
                    canvas = this.mHolder.lockCanvas();
                    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    this.drawArea(canvas);
                    this.drawRocker(canvas);
                    Thread.sleep((long) this.mRefreshCycle);
                } catch (Exception var6) {
                    var6.printStackTrace();
                } finally {
                    if (canvas != null) {
                        this.mHolder.unlockCanvasAndPost(canvas);
                    }

                }
            }

        }
    }

    private void drawArea(Canvas canvas) {
        if (null != this.mAreaBitmap) {
            this.mPaint.setColor(-16777216);
            Rect src = new Rect(0, 0, this.mAreaBitmap.getWidth(), this.mAreaBitmap.getHeight());
            Rect dst = new Rect(this.mAreaPosition.x - this.mAreaRadius, this.mAreaPosition.y - this.mAreaRadius, this.mAreaPosition.x + this.mAreaRadius, this.mAreaPosition.y + this.mAreaRadius);
            canvas.drawBitmap(this.mAreaBitmap, src, dst, this.mPaint);
        } else {
            this.mPaint.setColor(this.mAreaColor);
            canvas.drawCircle((float) this.mAreaPosition.x, (float) this.mAreaPosition.y, (float) this.mAreaRadius, this.mPaint);
        }

    }

    private void drawRocker(Canvas canvas) {
        if (null != this.mRockerBitmap) {
            this.mPaint.setColor(-16777216);
            Rect src = new Rect(0, 0, this.mRockerBitmap.getWidth(), this.mRockerBitmap.getHeight());
            Rect dst = new Rect(this.mRockerPosition.x - this.mRockerRadius, this.mRockerPosition.y - this.mRockerRadius, this.mRockerPosition.x + this.mRockerRadius, this.mRockerPosition.y + this.mRockerRadius);
            canvas.drawBitmap(this.mRockerBitmap, src, dst, this.mPaint);
        } else {
            this.mPaint.setColor(this.mRockerColor);
            canvas.drawCircle((float) this.mRockerPosition.x, (float) this.mRockerPosition.y, (float) this.mRockerRadius, this.mPaint);
        }

    }

    private void listenerCallback() {
        if (this.mListener != null) {
            if (this.mRockerPosition.x == this.mAreaPosition.x && this.mRockerPosition.y == this.mAreaPosition.y) {
                this.mListener.callback(2, -1, 0.0F);
            } else {
                float radian = MathUtils.getRadian(this.mAreaPosition, new Point(this.mRockerPosition.x, this.mRockerPosition.y));
                int angle = this.getAngleConvert(radian);
                float distance = (float) MathUtils.getDistance((float) this.mAreaPosition.x, (float) this.mAreaPosition.y, (float) this.mRockerPosition.x, (float) this.mRockerPosition.y);
                this.mListener.callback(2, angle, distance);
            }
        }

    }

    private int getAngleConvert(float radian) {
        int tmp = (int) Math.round((double) radian / 3.141592653589793D * 180.0D);
        return tmp < 0 ? -tmp : 180 + (180 - tmp);
    }

    protected void onDraw(Canvas canvas) {
        if (this.isInEditMode()) {
            canvas.drawColor(-1);
            this.drawArea(canvas);
            this.drawRocker(canvas);
        }

    }

    public int getAreaRadius() {
        return this.mAreaRadius;
    }

    public void setAreaRadius(int areaRadius) {
        this.mAreaRadius = areaRadius;
    }

    public int getRockerRadius() {
        return this.mRockerRadius;
    }

    public void setRockerRadius(int rockerRadius) {
        this.mRockerRadius = rockerRadius;
    }

    public Bitmap getAreaBitmap() {
        return this.mAreaBitmap;
    }

    public void setAreaBitmap(Bitmap areaBitmap) {
        this.mAreaBitmap = areaBitmap;
    }

    public Bitmap getRockerBitmap() {
        return this.mRockerBitmap;
    }

    public void setRockerBitmap(Bitmap rockerBitmap) {
        this.mRockerBitmap = rockerBitmap;
    }

    public int getRefreshCycle() {
        return this.mRefreshCycle;
    }

    public void setRefreshCycle(int refreshCycle) {
        this.mRefreshCycle = refreshCycle;
    }

    public int getCallbackCycle() {
        return this.mCallbackCycle;
    }

    public void setCallbackCycle(int callbackCycle) {
        this.mCallbackCycle = callbackCycle;
    }

    public int getAreaColor() {
        return this.mAreaColor;
    }

    public void setAreaColor(int areaColor) {
        this.mAreaColor = areaColor;
        this.mAreaBitmap = null;
    }

    public int getRockerColor() {
        return this.mRockerColor;
    }

    public void setRockerColor(int rockerColor) {
        this.mRockerColor = rockerColor;
        this.mRockerBitmap = null;
    }

    public void setListener(@NonNull RockerView.RockerListener listener) {
        this.mListener = listener;
    }

    public interface RockerListener {
        void callback(int var1, int var2, float var3);
    }
}
