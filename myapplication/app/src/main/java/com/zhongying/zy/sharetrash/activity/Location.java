package com.zhongying.zy.sharetrash.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.UserService.Utils.CoordinateUtil;


public class Location extends AppCompatActivity implements LocationSource,AMapLocationListener{
    private boolean first;
    //显示地图需要的变量
    private MapView mapView;//地图控件
    private AMap aMap;//地图对象
    private Context mContext=this;
    /** 声明mlocationClient对象 */
    private AMapLocationClient mlocationClient = null;

    /** 声明mLocationOption对象 */
    private AMapLocationClientOption mLocationOption = null;
    /** 定位监听器 */
    private OnLocationChangedListener mListener = null;

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();
        addMarkerToMap();
        initLocation();

    }

    private void addMarkerToMap() {
        double[] locate={108.987147,34.245585,108.998981,34.224713,108.963681,34.250827,108.94887,34.248934,
                108.936688,34.245277,108.935444,34.237487};
        String[] detail={"交大兴庆校区","电力设计院","和平门","世纪金花时代广场","糖果厂住宅区","西安市体育学院"};
        for(int i=5;i>=0;i--){
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(new LatLng(locate[i*2+1],locate[i*2]));
            markerOption.title(detail[i]);
            markerOption.draggable(true);
            aMap.addMarker(markerOption);
        }
    }

    /**
     * 获取定位坐标
     */
    public void initLocation() {
        initialPosition();
        //设置定位蓝点样式
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);
        //设置显示定位按钮 并且可以点击
        UiSettings settings = aMap.getUiSettings();
        aMap.setLocationSource(this);//设置了定位的监听,这里要实现LocationSource接口
        // 是否显示定位按钮
        settings.setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        //如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会。
        //mLocationOption.setOnceLocationLatest(true);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    public void initialPosition() {
        String lo="";
        if(first){
            lo=initialShare("");
            first=false;
        }
        else {
            SharedPreferences read = getSharedPreferences("locata", MODE_PRIVATE);
            lo=initialShare(read.getString("prelocation",""));
        }
        Toast.makeText(this,lo,Toast.LENGTH_SHORT).show();

        String[] complex=lo.split(",");
        String Latitude=complex[0];
        String Longtitude=complex[1];
        Double finalLatitude=Double.parseDouble(Latitude);
        Double finalLongtitude=Double.parseDouble(Longtitude);
        LatLng localLatLng=new LatLng(finalLatitude,finalLongtitude);
        this.aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng,18));
        }

    public String initialShare(String prelat) {
        SharedPreferences read = getSharedPreferences("locata", MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("locata", MODE_PRIVATE).edit();
        if(!"".equals(prelat)) {
            editor.putString("prelocation", prelat);
            editor.commit();
        }
        if("".equals(prelat)){
            editor.putString("prelocation", "30.248054,108.989356");
            editor.commit();
        }
        editor.commit();
        return read.getString("prelocation","");
    }

    public AMapLocation fromGpsToAmap(AMapLocation location) {
        LatLng latLng =null;
        latLng = CoordinateUtil.transform(location.getLatitude(), location.getLongitude());
        AMapLocation aMapLocation = new AMapLocation(location);
        aMapLocation.setLatitude(latLng.latitude);
        aMapLocation.setLongitude(latLng.longitude);
        return aMapLocation;
    }
    /**
     * 高德定位回调
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    //aMapLocation=fromGpsToAmap(aMapLocation);
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    initialShare(aMapLocation.getLatitude()+","+aMapLocation.getLongitude());
                    //Toast.makeText(getApplicationContext(), aMapLocation.getLatitude()+","+aMapLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    isFirstLoc = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlocationClient.onDestroy();
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener=onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener=null;
    }

}

