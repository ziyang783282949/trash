package com.zhongying.zy.sharetrash.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.LoginAndRegist;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.BaseObserver;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.NetworkBaseActivity;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.RetroFactory;
import com.zhongying.zy.sharetrash.UserService.UserInfo;
import com.zhongying.zy.sharetrash.interfacePackage.LoGin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
    private void getUsers() {
        String name = username.getText().toString();
        String pass = password.getText().toString();

        String newname="";
        String newpassword="";
        try {
            newname= URLEncoder.encode(name,"utf-8");
            newpassword=URLEncoder.encode(pass,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        UserInfo user=new UserInfo(newname,newpassword);
        Gson gson =new Gson();
        String route=gson.toJson(user);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        observable = RetroFactory.getInstance().Login("UserLogin",body);
        observable.compose(composeFunction).subscribe(new BaseObserver<UserInfo>(this,pd) {
            @Override
            public void onHandleSuccess(UserInfo userInfo) {
                Toast.makeText(getApplicationContext(),"a",Toast.LENGTH_LONG).show();
            }
        });


    }
public void Login(){
    String name=username.getText().toString();
    String pass=password.getText().toString();
    String newname="";
    String newpassword="";
    try {
        newname=URLEncoder.encode(name,"utf-8");
        newpassword=URLEncoder.encode(pass,"utf-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    UserInfo user=new UserInfo(newname,newpassword);
    Gson gson =new Gson();
    String route=gson.toJson(user);
    Log.i("info",route);
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("http://zyitem.ngrok.cc/transfer/servlet/")
            .build();
    LoGin log=retrofit.create(LoGin.class);
    RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
    Call<ResponseBody> call=log.login(body);
    call.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            String a="";
            try {
                a=response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
        }
    });}
public void Regist(){
    String name=username.getText().toString();
    String pass=password.getText().toString();
    String newname="";
    String newpassword="";
    try {
        newname=URLEncoder.encode(name,"utf-8");
        newpassword=URLEncoder.encode(pass,"utf-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    UserInfo user=new UserInfo(newname,newpassword);
    Gson gson =new Gson();
    String route=gson.toJson(user);
    Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    LoGin loGin=retrofit.create(LoGin.class);
    RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
    Observable<ResponseBody> call=loGin.regist(body);


    }

    Runnable login=new Runnable() {
    @Override
    public void run() {

        String name=username.getText().toString();
        String pass=password.getText().toString();
        String result= LoginAndRegist.login(name,pass,getApplicationContext());
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
            //new Thread(LoGin).start();
            //Login();
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
