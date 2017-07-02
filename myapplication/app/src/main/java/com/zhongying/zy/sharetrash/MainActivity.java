package com.zhongying.zy.sharetrash;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhongying.zy.sharetrash.loginActivity.RegistActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText username;
    private EditText password;
    private Button LogIn;
    private Button Regist;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        LogIn.setOnClickListener(this);
        Regist.setOnClickListener(this);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:{
                    Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_LONG).show();
                    break;
                }
                case 1:{
                    Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();
                    break;
                }
                case 2:{
                    Toast.makeText(getApplicationContext(),"网络连接错误",Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }
    };
    Runnable login=new Runnable() {
    @Override
    public void run() {

        String name=username.getText().toString();
        String pass=password.getText().toString();
        String result=Userservice.login(name,pass,getApplicationContext());
        ///未处理部分
        if(result!=null){
            if(result.equals("success")){
                handler.sendEmptyMessage(0);
            }
            if(result.equals("fail")){
                handler.sendEmptyMessage(1);
            }
            if(result.equals("error")){
                handler.sendEmptyMessage(2);
            }
        }
    }
};
    public void initButtons() {
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        LogIn= (Button) findViewById(R.id.logIn);
        Regist= (Button) findViewById(R.id.Regist);
        transaction=getSupportFragmentManager().beginTransaction();
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.logIn:{
            new Thread(login).start();
            break;
        }
        case R.id.Regist:{
            Intent intent=new Intent(this, RegistActivity.class);
            startActivity(intent);
            break;
        }
}
    }
}
