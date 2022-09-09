package com.yyds.multichat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import com.bumptech.glide.Glide;
import com.yyds.multichatlayout.MultiVideoChatLayout;
import java.util.ArrayList;
import java.util.List;
import cn.jzvd.JzvdStd;

public class MainActivity extends AppCompatActivity {

    MultiVideoChatLayout multiVideoChatLayout;
    private Runnable mAddSettingWindowRunnable;
    private Runnable mRemoveSettingWindowRunnable;
    private static final long INTERVALMILLIS = 10 * 1000;

    private List<String> mImageUrlList = new ArrayList<>();
    private List<String> mVideoUrlList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private int position = 0;
    private boolean FLAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
        settingWindow();
    }

    private void init() {
        multiVideoChatLayout = findViewById(R.id.mcl_layout);
        mImageUrlList.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAOEcdM.img");
        mImageUrlList.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAOEcgc.img");
        mImageUrlList.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAOEhXT.img");
        mImageUrlList.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAOEhRG.img");
        mImageUrlList.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAOEe5M.img");
        mImageUrlList.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAOEiHa.img");

        mVideoUrlList.add("https://prod-streaming-video-msn-com.akamaized.net/a8c412fa-f696-4ff2-9c76-e8ed9cdffe0f/604a87fc-e7bc-463e-8d56-cde7e661d690.mp4");
        mVideoUrlList.add("https://prod-streaming-video-msn-com.akamaized.net/ba258271-89c7-47bc-9742-bcae67c23202/f7ff4fe4-1346-47bb-9466-3f4662c1ac3a.mp4");
        mVideoUrlList.add("https://prod-streaming-video-msn-com.akamaized.net/b7014b7e-b38f-4a64-bd95-4a28a8ef6dee/113a2bf3-3a5f-45d4-8b6f-e40ce8559da3.mp4");
        mVideoUrlList.add("https://prod-streaming-video-msn-com.akamaized.net/0b927d99-e38a-4f51-8d1a-598fd4d6ee97/3493c85c-f35a-488f-9a8f-633e747fb141.mp4");
        mVideoUrlList.add("https://prod-streaming-video-msn-com.akamaized.net/bc3e9341-3243-4d2c-8469-940fef56ca2d/4720a02b-eabd-4593-a1d9-5c5d61916853.mp4");
        mVideoUrlList.add("https://prod-streaming-video-msn-com.akamaized.net/35960fe4-724f-44fc-ad77-0b91c55195e4/bfd49cd7-a0c6-467e-ae34-8674779e689b.mp4");

        mTitleList.add("one");
        mTitleList.add("two");
        mTitleList.add("three");
        mTitleList.add("four");
        mTitleList.add("five");
        mTitleList.add("six");
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (FLAG) {
                multiVideoChatLayout.removeViewAt(msg.what);
            } else {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_multi_window, null);
                JzvdStd jzvdStd = view.findViewById(R.id.jz_video);
                jzvdStd.setUp(mVideoUrlList.get(msg.what), mTitleList.get(msg.what));
                Glide.with(MainActivity.this).load(mImageUrlList.get(msg.what)).into(jzvdStd.thumbImageView);
                multiVideoChatLayout.addView(view);
                jzvdStd.startVideo();
            }
        }
    };

    private void settingWindow() {
        if (mAddSettingWindowRunnable != null) return;
        mAddSettingWindowRunnable = () -> {
            FLAG = false;
            mHandler.sendEmptyMessage(position);
            position++;
            if (position < 6) {
                mHandler.postDelayed(mAddSettingWindowRunnable, INTERVALMILLIS);
            } else {
                mHandler.postDelayed(mRemoveSettingWindowRunnable, INTERVALMILLIS);
            }
        };
        if (mRemoveSettingWindowRunnable != null) return;
        mRemoveSettingWindowRunnable = () -> {
            FLAG = true;
            position--;
            mHandler.sendEmptyMessage(position);
            if (position < 1) {
                mHandler.postDelayed(mAddSettingWindowRunnable, INTERVALMILLIS);
            } else {
                mHandler.postDelayed(mRemoveSettingWindowRunnable, INTERVALMILLIS);
            }
        };
        mHandler.postDelayed(mAddSettingWindowRunnable,INTERVALMILLIS);
    }
}