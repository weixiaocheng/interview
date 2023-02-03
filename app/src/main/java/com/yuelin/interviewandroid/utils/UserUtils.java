package com.yuelin.interviewandroid.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Pattern;

public class UserUtils {
    /**
     * 检查用户输入的信息是否完整
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    public static boolean validateLogin(Context context, String phone, String password) {
        if (phone == null || phone.trim().length() == 0) {
            Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Pattern.matches("^1\\d{10}", phone)) {
            Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (password == null || password.trim().length() == 0) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        
        return true;
    }


}
