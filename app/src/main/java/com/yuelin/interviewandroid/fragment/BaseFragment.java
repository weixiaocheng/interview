package com.yuelin.interviewandroid.fragment;

import android.os.Looper;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    protected void showToastWithMsg(String msg) {
        // 获取当前线程
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
