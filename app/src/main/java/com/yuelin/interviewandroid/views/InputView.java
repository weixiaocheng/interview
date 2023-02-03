package com.yuelin.interviewandroid.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yuelin.interviewandroid.R;

public class InputView extends LinearLayout {

    private Boolean isPassword;
    private View mView;
    private String inputHint;
    private int inputIcon;

    // 定义引用对象
    private EditText editText;
    // 定义左侧图标
    private ImageView leftIcon;

    public InputView(Context context) {
        super(context);
        init(context, null);
    }

    public InputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public InputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * inputView 组件初始化
     *
     * @param context
     * @param atts
     */
    private void init(Context context, AttributeSet atts) {
        if (atts == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(atts, R.styleable.InputView);
        isPassword = typedArray.getBoolean(R.styleable.InputView_input_is_password, false);
        inputIcon = typedArray.getResourceId(R.styleable.InputView_input_icon, R.mipmap.phone);
        inputHint = typedArray.getString(R.styleable.InputView_input_hint);

        mView = LayoutInflater.from(context).inflate(R.layout.view_input, this, false);

        editText = mView.findViewById(R.id.input_edit);
        leftIcon = mView.findViewById(R.id.input_icon);

        leftIcon.setImageResource(inputIcon);
        editText.setHint(inputHint);
        editText.setInputType(isPassword ? InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT : InputType.TYPE_CLASS_PHONE);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(isPassword ? 16 : 11)});

        addView(mView);
    }

    /**
     * 获取用户输入的信息
     * @return
     */
    public String getString() {
        return editText.getText().toString().trim();
    }

    /**
     * 填充输入内容
     * @param text
     */
    public void setString(String text) {
        if (editText != null) {
            editText.setText(text);
        }
    }
}
