package com.zhongying.zy.sharetrash.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.LocationSource;
import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.activity.Location;

/**
 * Created by DELL on 2017/7/8.
 */

public class TwoFragment extends Fragment {
private TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_two,container,false);
        tv= (TextView) view.findViewById(R.id.zz);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Location.class));
            }
        });
        return view;
    }
    public static TwoFragment newInstance(){
        TwoFragment fragment=new TwoFragment();
        return fragment;
    }
}
