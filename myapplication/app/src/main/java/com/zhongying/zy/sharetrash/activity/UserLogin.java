package com.zhongying.zy.sharetrash.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.BaseObserver;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.NetworkBaseActivity;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.RetroFactory;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.SharedPreferencesUtils;
import com.zhongying.zy.sharetrash.UserService.UserInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class UserLogin extends NetworkBaseActivity implements View.OnClickListener{
    private EditText username;
    private EditText password;
    private Button LogIn;
    private Button Regist;
    private FragmentTransaction transaction;
    private Observable observable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        getPreUserInfo();
        LogIn.setOnClickListener(this);
        Regist.setOnClickListener(this);
    }

    private void getPreUserInfo() {
        /*SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String userinfo=preferences.getString("userinfo","");*/
        String userinfo= (String) SharedPreferencesUtils.getParam(UserLogin.this,"String","");
        Gson gson=new Gson();
        UserInfo user2=new UserInfo();
        Log.i("info",userinfo);
        user2=gson.fromJson(userinfo,UserInfo.class);
        if(user2!=null){
            username.setText(user2.getUsername());
            password.setText(user2.getPassword());
        }
    }

    private void getUsers() {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        String newname=name;
        String newpassword=pass;
       /* try {
            newname= URLEncoder.encode(name,"utf-8");
            newpassword=URLEncoder.encode(pass,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        UserInfo user=new UserInfo(newname,newpassword);
        Gson gson =new Gson();
        String route=gson.toJson(user);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        observable = RetroFactory.getInstance().Login("UserLogin",body);
        observable.compose(composeFunction).subscribe(new BaseObserver<UserInfo>(this,pd) {
            @Override
            public void onHandleSuccess(UserInfo userInfo) {
                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_LONG).show();
            }
        });
    }
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
            getUsers();
            break;
        }
        case R.id.Regist:{
            Intent intent=new Intent(this, UserRegist.class);
            startActivity(intent);
            break;
        }
}
    }
}
