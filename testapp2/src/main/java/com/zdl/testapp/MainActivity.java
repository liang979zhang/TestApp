package com.zdl.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.zdl.testapp.test.Teacher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private Button btn, btn2;
    private Chronometer chronometer;

    int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "onCreate: ");

//        String jsonData = "[\n" +
//                "    {\n" +
//                "        \"regionId\": 330324,\n" +
//                "        \"orderListVOList\": [\n" +
//                "            {\n" +
//                "                \"orderId\": 14,\n" +
//                "                \"userNumber\": 8773330300005,\n" +
//                "                \"regionId\": 330324,\n" +
//                "                \"organizationId\": 26,\n" +
//                "                \"orderStatusId\": 2,\n" +
//                "                \"orderTypeId\": 0,\n" +
//                "                \"orderAmount\": 0,\n" +
//                "                \"deadlineTime\": 0,\n" +
//                "                \"linkMan\": \"订单撤销\",\n" +
//                "                \"linkPhone\": \"15858261319\",\n" +
//                "                \"linkAddress\": \"订单撤销测试接口专用订单\",\n" +
//                "                \"orderContent\": [{\n" +
//                "                    \"contentId\": 0,\n" +
//                "                    \"orderId\": 0,\n" +
//                "                    \"specificationId\": \"YSP35.5\",\n" +
//                "                    \"num\": 1\n" +
//                "                }]\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"orderId\": 333,\n" +
//                "                \"userNumber\": 8773330300004,\n" +
//                "                \"regionId\": 330324,\n" +
//                "                \"organizationId\": 26,\n" +
//                "                \"orderStatusId\": 1,\n" +
//                "                \"orderTypeId\": 0,\n" +
//                "                \"orderAmount\": 0,\n" +
//                "                \"deadlineTime\": 0,\n" +
//                "                \"linkMan\": \"可处理订单\",\n" +
//                "                \"linkPhone\": \"15858261319\",\n" +
//                "                \"linkAddress\": \"可处理订单\",\n" +
//                "                \"orderContent\": [{\n" +
//                "                    \"contentId\": 0,\n" +
//                "                    \"orderId\": 0,\n" +
//                "                    \"specificationId\": \"YSP35.5\",\n" +
//                "                    \"num\": 1\n" +
//                "                }]\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"regionId\": 330300,\n" +
//                "        \"orderListVOList\": [{\n" +
//                "            \"orderId\": 331,\n" +
//                "            \"userNumber\": 8773330300001,\n" +
//                "            \"regionId\": 330300,\n" +
//                "            \"organizationId\": 26,\n" +
//                "            \"orderStatusId\": 2,\n" +
//                "            \"orderTypeId\": 0,\n" +
//                "            \"orderAmount\": 0,\n" +
//                "            \"deadlineTime\": 0,\n" +
//                "            \"linkMan\": \"钟金荣\",\n" +
//                "            \"linkPhone\": \"15967751724\",\n" +
//                "            \"linkAddress\": \"浙江省永嘉县岭头乡岭南村环村东路40号\",\n" +
//                "            \"orderContent\": [{\n" +
//                "                \"contentId\": 0,\n" +
//                "                \"orderId\": 0,\n" +
//                "                \"specificationId\": \"YSP35.5\",\n" +
//                "                \"num\": 1\n" +
//                "            }]\n" +
//                "        }]\n" +
//                "    }\n" +
//                "]";
//
//
//        Type listType = new TypeToken<LinkedList<Aa>>() {
//        }.getType();
//        Gson gson = new Gson();
//        LinkedList<Aa> la = gson.fromJson(jsonData, listType);
//        List<Aa> aas = new ArrayList<>();
//        List<Aa> aas2 = new ArrayList<>();
//
//        for (int i = 0; i < la.size(); i++) {
//            Aa aa = la.get(i);
//            aas.add(aa);
//
//        }
//        List<Aa.OrderListVOListBean> orderListVOList = aas.get(0).getOrderListVOList();
//        List<Aa.OrderListVOListBean> orderList = new ArrayList<>();
//        for (int i = 0; i < orderListVOList.size(); i++) {
//            Aa.OrderListVOListBean orderListVOListBean = orderListVOList.get(i);
//            orderList.add(orderListVOListBean);
//        }
//
//        orderList.add(aas.get(aas.size()).getOrderListVOList().get(0));
        //adaper直接传入orderList就行
        chronometer = findViewById(R.id.textView2);
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                second++;
                chronometer.setText(FormatMiss(second));
            }
        });

        final Teacher teacher = new Teacher();
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));

                Toast.makeText(MainActivity.this, teacher.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TabActivity.class));
            }
        });


    }


    //格式转换为HH:MM:SS
    public static String FormatMiss(int second) {
        if (second == 0) {
            return "00:00:00";
        }
        String hh = second / 3600 > 9 ? second / 3600 + "" : "0" + second / 3600;
        String mm = (second % 3600) / 60 > 9 ? (second % 3600) / 60 + "" : "0" + (second % 3600) / 60;
        String ss = (second % 3600) % 60 > 9 ? (second % 3600) % 60 + "" : "0" + (second % 3600) % 60;
        return hh + ":" + mm + ":" + ss;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    static class GsonUtil {
        //将Json数据解析成相应的映射对象
        public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
            Gson gson = new Gson();
            T result = gson.fromJson(jsonData, type);
            return result;
        }

    }

}
