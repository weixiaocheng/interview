package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.utils.UserUtils;
import com.yuelin.interviewandroid.views.InputView;

public class LoginActivity extends BaseActivity {

    private String phone , password;
    private InputView phoneIv, passwordIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        phoneIv = findViewById(R.id.login_phone);
        passwordIv = findViewById(R.id.login_password);
        phoneIv.setString("15361842011");
        passwordIv.setString("123456");
    }

    public void login(View view) {
        Log.i(TAG, "login: 点击了用户登录");
        password = passwordIv.getString();
        phone = phoneIv.getString();
        boolean result = UserUtils.validateLogin(this, phone, password);
        if (!result) {
            return;
        }
        Log.i(TAG, "login: 登录成功");
        // 注意这里一定要调用show方法
        Toast.makeText(this, "登录成功?", Toast.LENGTH_SHORT).show();
        // 开始解决网络请求的问题
//        toTabbar();
    }
    
    public void register(View view) {
        Log.i(TAG, "register: 点击了用户注册去注册页面 -- 先不处理");
    }

    private void toTabbar() {
        startActivity(new Intent(this, TabbarActivity.class));
        finish();
    }
}