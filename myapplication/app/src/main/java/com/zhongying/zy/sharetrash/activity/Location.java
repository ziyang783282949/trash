package com.zhongying.zy.sharetrash.activity;


import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolylineOptions;
import com.zhongying.zy.sharetrash.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Location extends AppCompatActivity {
        //显示地图需要的变量
        private MapView mapView;//地图控件
        private AMap aMap;//地图对象

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mapView= (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        aMap=mapView.getMap();

        addMarkerToMap();

    }
    private void addMarkerToMap() {
        LatLng latLng = new LatLng(39.9081728469, 116.3867845961);
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marker));
        Marker marker = aMap.addMarker(markerOption);
        marker.setRotateAngle(30);
    }
}

