package com.zhongying.zy.sharetrash.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.NetworkBaseActivity;

/**
 * Created by zy on 2017/7/16.
 */

public class UserProfile extends NetworkBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        setTitle("标题");
        showBackwardView(R.string.text_back,true);
        showForwardView(R.string.text_forward, true);
        /**
         * 提交按钮点击后触发
         * @param forwardView
         */
    }
    protected void onForward(View forwardView) {
        Toast.makeText(this, "提交", Toast.LENGTH_LONG).show();
    }
}
