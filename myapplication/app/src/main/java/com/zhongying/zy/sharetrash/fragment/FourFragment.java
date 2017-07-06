package com.zhongying.zy.sharetrash.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.activity.StartActivity;
import com.zhongying.zy.sharetrash.activity.UserLogin;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment implements View.OnClickListener{
    private LinearLayout loginButton;

    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_four, container, false);
        init(view);
        loginButton.setOnClickListener(this);
        return view;
    }
    public static FourFragment newInstance(){
        FourFragment fragment=new FourFragment();
        return fragment;
    }
   public void init(View view){
       loginButton= (LinearLayout) view.findViewById(R.id.loginButton);
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:{
                Intent intent=new Intent(getActivity(), UserLogin.class);
                startActivity(intent);
            }
        }
    }
}
