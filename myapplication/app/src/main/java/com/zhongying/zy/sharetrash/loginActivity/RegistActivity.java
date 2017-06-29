package com.zhongying.zy.sharetrash.loginActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.Userservice;

/**
 * Created by zy on 2017/6/29.
 */

public class RegistActivity extends AppCompatActivity implements View.OnClickListener{
    private Button regist;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        initButton();
        regist.setOnClickListener(this);
    }



    public void initButton() {
        regist = (Button) findViewById(R.id.Regist);
        username = (EditText) findViewById(R.id.LoginName);
        password = (EditText) findViewById(R.id.LoginPassword);

    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                }
                case 1: {
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
        Runnable regidButton = new Runnable() {
            @Override
            public void run() {

                String name = username.getText().toString();
                String pass = password.getText().toString();
                Boolean result = Userservice.check(name, pass, getApplicationContext());
                if (result) {
                    handler.sendEmptyMessage(0);
                } else {
                    handler.sendEmptyMessage(1);
                }
            }

    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Regist:{

                new Thread(regidButton).start();
                //Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
            }
        }
    }
}



