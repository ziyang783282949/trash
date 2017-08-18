package com.zhongying.zy.sharetrash.ReferenceRetrofit;

import com.zhongying.zy.sharetrash.UserService.UserInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitService {
    /**
     * 用户登录
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("{config}")
    Observable<BaseEntity<UserInfo>> Login(@Path("config") String config, @Body RequestBody user);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("{config}")
    Observable<BaseEntity<UserInfo>> Regist(@Path("config") String config,@Body RequestBody user);

    /**
     * 上传头像
     */
    @Multipart
    @POST("{config}")
    Observable<BaseEntity<UserInfo>> uploadMemberIcon(@Path("config") String config,@Part("data") String des,@Part List<MultipartBody.Part> partList);

}
