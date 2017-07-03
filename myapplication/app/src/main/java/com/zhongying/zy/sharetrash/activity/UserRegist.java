package com.zhongying.zy.sharetrash.activity;

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
import com.zhongying.zy.sharetrash.LoginAndRegist;

/**
 * Created by zy on 2017/6/29.
 */

public class UserRegist extends AppCompatActivity implements View.OnClickListener{
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
                    Toast.makeText(getApplicationContext(), "用户已存在", Toast.LENGTH_LONG).show();
                    break;
                }
                case 1: {
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                    break;
                }
                case 2: {
                    Toast.makeText(getApplicationContext(), "网络未连接", Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }
    };
        Runnable regidButton = new Runnable() {
            @Override
            public void run() {

                String name = username.getText().toString();
                String pass = password.getText().toString();
                String result = LoginAndRegist.regist(name, pass, getApplicationContext());
                if(result!=null){
                    if(result.equals("userExist")){
                        handler.sendEmptyMessage(0);
                    }
                    if(result.equals("success")){
                        handler.sendEmptyMessage(1);
                    }
                    if(result.equals("error")){
                        handler.sendEmptyMessage(2);
                    }
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



