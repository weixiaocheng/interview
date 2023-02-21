package com.yuelin.interviewandroid.utils;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.logging.LogRecord;

public class Utils {
    public static void log(String e) {
        Log.i("i", e);
    }

    public static String base64toString(String ba64) {
        byte[] decodedBytes = Base64.getDecoder().decode(ba64);
        return new String(decodedBytes);
    }


    /**
     * 功能：检测当前URL是否可连接或是否有效,
     * 描述：最多连接网络 x次, 如果 x 次都不成功，视为该地址不可用
     *
     * @return true是可以上网，false是不能上网
     */
    public static void isNetOnline(final String address, final Comparable<Boolean> callback) {



        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (callback != null) {
                    callback.compareTo(msg.arg1 >= 0);
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                int counts = 0;
                URL url;
                HttpURLConnection con;
                int state = -1;
                Message msg = new Message();

                while (counts < 2) {
                    try {
                        url = new URL(address);
                        con = (HttpURLConnection) url.openConnection();
                        state = con.getResponseCode();
                        Log.e("FragmentNet", "isNetOnline counts: " + counts + "=state: " + state);
                        if (state == 200) {
                            msg.arg1 = 1;
                        }
                        break;
                    } catch (Exception ex) {
                        msg.arg1 = -1;
                        counts++;
                        Log.e("FragmentNet", "isNetOnline URL不可用，连接第 " + counts + " 次");
                        continue;
                    }
                }
                handler.sendMessage(msg);
            }
        }).start();


//        return isNetsOnline;

    }
}
