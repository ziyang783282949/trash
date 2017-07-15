package com.zhongying.zy.sharetrash.interfacePackage;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by zy on 2017/7/15.
 */

public interface LoGin {
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("UserLogin")
    Call<ResponseBody> login(@Body RequestBody user);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("")
    Observable<ResponseBody> regist(@Body RequestBody user);
}
