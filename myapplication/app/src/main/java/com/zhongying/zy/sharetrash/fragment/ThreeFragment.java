package com.zhongying.zy.sharetrash.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongying.zy.sharetrash.R;

/**
 * Created by zy on 2017/7/10.
 */

public class ThreeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_three,container,false);
        return view;
    }
    public static ThreeFragment newInstance(){
        ThreeFragment fragment=new ThreeFragment();
        return fragment;
    }
}
