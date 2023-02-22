package com.yuelin.interviewandroid.fragment.categoryList;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.yuelin.interviewandroid.utils.Utils.getHtmlData;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.fragment.BaseFragment;
import com.yuelin.interviewandroid.model.QuestDetailRespose;
import com.yuelin.interviewandroid.network.NetworkManager;
import com.yuelin.interviewandroid.utils.Utils;
import com.yuelin.interviewandroid.views.NavigationBar;

import java.util.Base64;

public class QuestDetailFragment extends BaseFragment implements View.OnClickListener {

    private NavigationBar navigationBar;

    private WebView webView;

    private int questId;

    private CategoryItemClickCallBack itemClickCallBack;

    private QuestChangeLisenter questChangeLisenter;

    public void setQuestChangeLisenter(QuestChangeLisenter questChangeLisenter) {
        this.questChangeLisenter = questChangeLisenter;
    }

    private Gson mgson = new Gson();

    private int currentIndex;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }


    public void setItemClickCallBack(CategoryItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }


    public void setQuestId(int questId) {
        this.questId = questId;
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quest_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        navigationBar = view.findViewById(R.id.nav_bar);
        navigationBar.setTitle("--");
        navigationBar.setIsBack(true);
        navigationBar.setBackIconOnClick(this);

        webView = view.findViewById(R.id.web_view_id);
        Button nextBtn = view.findViewById(R.id.next_btn);
        Button previousBtn = view.findViewById(R.id.previous_btn);
        nextBtn.setOnClickListener(this);
        previousBtn.setOnClickListener(this);
    }

    // 设置标题
    public void setTitle(String title) {
        navigationBar.setTitle(title);
    }

    // 加载网页内容
    public void setWebUrl(String string) {
        webView.loadData(Utils.getHtmlData(string), "text/html;charset=utf-8", "utf-8");
    }

    public void loadData() {
        NetworkManager.getInstance.getQuestDetail(questId, new NetworkManager.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                QuestDetailRespose questDetailRespose = mgson.fromJson(response, QuestDetailRespose.class);
                String base64Str = questDetailRespose.data.html;
                byte[] decodedBytes = Base64.getDecoder().decode(base64Str);
                String html = new String(decodedBytes);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadData(getHtmlData(html), "text/html;charset=utf-8", "utf-8");
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                showToastWithMsg(e.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_icon) {
            if (itemClickCallBack != null) {
                itemClickCallBack.itemOnClick(null, 0);
            }
        }else if (v.getId() == R.id.previous_btn) {
            if (questChangeLisenter != null) {
                questChangeLisenter.previousQuestion();
            }
        } else if (v.getId() == R.id.next_btn) {
            if (questChangeLisenter != null) {
                questChangeLisenter.nextQuest();
            }
        }else {
            Log.e(TAG, "onClick: 找不到id" );
        }
    }
}
