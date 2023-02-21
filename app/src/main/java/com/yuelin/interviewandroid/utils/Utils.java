package com.yuelin.interviewandroid.utils;

import android.util.Log;

import java.util.Base64;

public class Utils {
    public static void log(String e) {
        Log.i("i", e);
    }

    public static String base64toString(String ba64) {
        byte[] decodedBytes = Base64.getDecoder().decode(ba64);
        return new String(decodedBytes);
    }
}
