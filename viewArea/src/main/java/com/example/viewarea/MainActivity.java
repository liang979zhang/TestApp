package com.example.viewarea;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
/**
 * 
 * @作者： guoss
 * @修改时间：2016-4-11 下午6:36:53 	
 * @包名：com.example.viewarea
 * @文件名：MainActivity.java
 * @版权声明：www.ekwing.com
 * @功能：双指缩放 单指拖拽 边界回弹
 */
public class MainActivity extends Activity {

	private LinearLayout ll_viewArea;
	private LayoutParams parm;
	private ViewArea viewArea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  //去除title   
        requestWindowFeature(Window.FEATURE_NO_TITLE);
         //去掉Activity上面的状态栏 
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN, 
                       WindowManager.LayoutParams. FLAG_FULLSCREEN);
       
//        这段代码中要注意的问题是去掉title和状态栏两句代码必须放到 setContentView(R.layout.main);话的前面。
//        	而且这两句话必须有，因为后面计算回弹距离是根据全屏计算的（我的i9000就是480x800），如果不去掉title和状态栏，后面的回弹会有误差，总是回弹不到想要的位置。 
		setContentView(R.layout.activity_main);
       
        ll_viewArea = (LinearLayout) findViewById(R.id.ll_viewArea);
        parm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        viewArea = new ViewArea(MainActivity.this,R.drawable.c);    //自定义布局控件，用来初始化并存放自定义imageView
        ll_viewArea.addView(viewArea,parm);

	}
}
