package com.zhongying.zy.sharetrash.interfacePackage;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zy on 2017/7/11.
 */

public interface PostLogin {
    @POST("Kass")
    @FormUrlEncoded
    Call<ResponseBody> returnResult(@Field("name")String username, @Field("password")String pass);

}
