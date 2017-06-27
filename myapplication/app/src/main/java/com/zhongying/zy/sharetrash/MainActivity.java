package com.zhongying.zy.sharetrash;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        click();
    }
    public void click() {
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(login).start();
            }
        });
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:{
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                }
                case 1:{
                    Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
                }
            }

        }
    };
    Runnable login=new Runnable() {
    @Override
    public void run() {

        String name=username.getText().toString();
        String pass=password.getText().toString();
        Boolean result=Userservice.check(name,pass,getApplicationContext());
        if(result){
            handler.sendEmptyMessage(0);
        }
        else{
            handler.sendEmptyMessage(1);
        }
    }
};
    public void init() {
        regist= (Button) findViewById(R.id.regist);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
    }
}
