package com.zhongying.zy.sharetrash.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.interfacePackage.PostLogin;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by zy on 2017/7/10.
 */

public class ThreeFragment extends Fragment {
    private ImageView first;
    private TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_three,container,false);
        first= (ImageView) view.findViewById(R.id.first);
        tv= (TextView) view.findViewById(R.id.textView);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hahaas();
                //Toast.makeText(getActivity(),"dasd",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
    public void hahaas(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://zyitem.ngrok.cc/transfer/servlet/")
                .build();
        PostLogin postLogin=retrofit.create(PostLogin.class);
        Call<ResponseBody> call=postLogin.returnResult("资洋","123");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String a="";
                try {
                    a=response.body().string().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tv.setText(a);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public static ThreeFragment newInstance(){
        ThreeFragment fragment=new ThreeFragment();
        return fragment;
    }
}
