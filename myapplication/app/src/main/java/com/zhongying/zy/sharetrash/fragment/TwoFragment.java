package com.zhongying.zy.sharetrash.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.activity.Location;
import com.zhongying.zy.sharetrash.adapter.UserAdapter;
import com.zhongying.zy.sharetrash.entity.User;
import com.zhongying.zy.sharetrash.widge.ClearEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/7/8.
 */

public class TwoFragment extends Fragment implements View.OnClickListener{
    private ClearEditText et_search;
    private ListView user_list;
    private ImageView map;

    private String[] data;
    private ArrayAdapter<String> mAdapter;

    private List<User> mDatas = new ArrayList<>();
    private UserAdapter mUserAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_two,container,false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        et_search = (ClearEditText) view.findViewById(R.id.et_search);
        user_list = (ListView) view.findViewById(R.id.user_list);
        map= (ImageView) view.findViewById(R.id.map);
        map.setOnClickListener(this);
        initListView();
        intiEditView();
    }
    private void intiEditView() {
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mAdapter.getFilter().filter(s);

                mUserAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initListView() {
        mUserAdapter = new UserAdapter(getActivity(),mDatas);
        user_list.setAdapter(mUserAdapter);
    }

    private void initData() {
        data = new String[]{"张无忌", "周芷若", "赵敏", "东方不败", "令狐冲", "上官婉儿",
                "纳兰容若", "张李丹妮", "任盈盈"};

        User user1 = new User("西安交通大学兴庆校区","咸宁西路28号东南门运动场");
        mDatas.add(user1);
        User user2 = new User("陕西省电力设计院","西安市雁塔区电力设计院");
        mDatas.add(user2);
        User user3 = new User("和平门(地铁站附近)", "西安市碑林区雁塔北路和平门地铁站附近");
        mDatas.add(user3);
        User user4 = new User("世纪金花时代广场","西安市碑林区南关正街世纪金花时代广场");
        mDatas.add(user4);
        User user5 = new User("糖果厂住宅区", "碑林区围墙巷糖果厂住宅区");
        mDatas.add(user5);
        User user6 = new User("西安市体育学院本部", "西安市碑林区光大路体育学院");
        mDatas.add(user6);
        User user7 = new User("糖果厂住宅区", "碑林区围墙巷糖果厂住宅区");
        mDatas.add(user7);
        User user8 = new User("西安市体育学院本部", "西安市碑林区光大路体育学院");
        mDatas.add(user8);
        User user9 = new User("和平门(地铁站附近)", "西安市碑林区雁塔北路和平门地铁站附近");
        mDatas.add(user9);
        User user10 = new User("陕西省电力设计院","西安市雁塔区电力设计院");
        mDatas.add(user10);
    }

    public static TwoFragment newInstance(){
        TwoFragment fragment=new TwoFragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.map:{
                startActivity(new Intent(getActivity(), Location.class));
            }
        }
    }
}
