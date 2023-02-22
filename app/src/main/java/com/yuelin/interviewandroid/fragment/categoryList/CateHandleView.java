package com.yuelin.interviewandroid.fragment.categoryList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yuelin.interviewandroid.R;

public class CateHandleView {
    public TextView titleLb, NoLb;

    public static CateHandleView init(View view) {
        CateHandleView handleView = new CateHandleView();
        handleView.titleLb = (TextView) view.findViewById(R.id.quest_title_id);
        handleView.NoLb = (TextView) view.findViewById(R.id.quest_id);
        return handleView;
    }
}
