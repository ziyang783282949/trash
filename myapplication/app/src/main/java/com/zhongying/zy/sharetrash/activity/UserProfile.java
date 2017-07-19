package com.zhongying.zy.sharetrash.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhongying.zy.sharetrash.R;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.BaseObserver;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.NetworkBaseActivity;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.RetroFactory;
import com.zhongying.zy.sharetrash.ReferenceRetrofit.SharedPreferencesUtils;
import com.zhongying.zy.sharetrash.UserService.UserInfo;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.zhongying.zy.sharetrash.R.id.imageView;
import static com.zhongying.zy.sharetrash.R.id.man;

/**
 * Created by zy on 2017/7/16.
 */

public class UserProfile extends NetworkBaseActivity {
    private ImageView imgShow = null;
    private TextView imgPath = null;
    private Observable observable;
    private CheckBox man;
    private CheckBox woman;
    private boolean isman=true;
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private final int IMAGE_CODE = 0; // 这里的IMAGE_CODE是自己任意定义的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        initialButton();
        initialInfomation();
    }

    private void initialInfomation() {

        setTitle("编辑个人信息");
        showBackwardView(R.string.text_back,true);
        showForwardView(R.string.text_forward, true);
    }

    private void initialButton() {
        imgShow= (ImageView) findViewById(R.id.picture);
        imgPath= (TextView) findViewById(R.id.textView3);
        man= (CheckBox) findViewById(R.id.man);
        woman= (CheckBox) findViewById(R.id.woman);
        showImage(imgShow);
    }

    private void showImage(ImageView imgShow) {
        imgShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage1();
            }
        });
    }

    /**
     * 提交按钮点击后触发
     * @param forwardView
     */
    protected void onForward(View forwardView) {
        Toast.makeText(this, "提交", Toast.LENGTH_LONG).show();
        upLoad();
    }
    /**
     * 返回按钮点击后触发
     * @param backwardView
     */
    protected void onBackward(View backwardView) {
        imgPath.setText(man.isChecked()+"");
        //finish();
    }
    private void setImage1() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
        startActivityForResult(intent, IMAGE_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        Bitmap bm = null;
        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver = getContentResolver();
        if (requestCode == IMAGE_CODE) {
            try {
                Uri originalUri = data.getData(); // 获得图片的uri
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                //imgShow.setImageBitmap(ThumbnailUtils.extractThumbnail(bm, 100, 100));  //使用系统的一个工具类，参数列表为 Bitmap Width,Height  这里使用压缩后显示，否则在华为手机上ImageView 没有显示
                // 显得到bitmap图片
                imgShow.setImageBitmap(bm);
                String[] proj = { MediaStore.Images.Media.DATA };
                // 好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor c=null;
                if(originalUri.getScheme().equals("content")) {//判断uri地址是以什么开头的
                    c= resolver.query(originalUri, null, null, null, null);
                }else{
                    c= resolver.query(getFileUri(originalUri), null, null, null, null);//红色字体判断地址如果以file开头
                }
                c.moveToFirst();
                int colunm_index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                String srcPath = c.getString(colunm_index);
                //这是获取的图片保存在sdcard中的位置

                imgPath.setText(srcPath);
            } catch (IOException e) {
                Log.e("TAG-->Error", e.toString());
            }
            finally {
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void upLoad() {
        File file = new File(imgPath.getText().toString());//filePath 图片地址
        String token = "ASDDSKKK19990SDDDSS";//用户token
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
                //.addFormDataPart(ParamKey.TOKEN, token);//ParamKey.TOKEN 自定义参数key常量类，即参数名
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("imgfile", "usericon"+file.getName().substring(file.getName().lastIndexOf(".")), imageBody);//imgfile 后台接收图片流的参数名

        List<MultipartBody.Part> parts = builder.build().parts();

        UserInfo user=new UserInfo();
        String userinfo= (String) SharedPreferencesUtils.getParam(UserProfile.this,"String","");
        Gson gson=new Gson();
        UserInfo user2=new UserInfo();

        user2=gson.fromJson(userinfo,UserInfo.class);
        String username = "";
        String password="";
        try {
             username=URLEncoder.encode(user2.getUsername(),"utf-8");
            password=URLEncoder.encode(user2.getPassword(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setUsername(username);
        user.setPassword(password);
        if(man.isChecked()){

        }
        user.setSex(man.isChecked()?"1":"0");
        user.setUrlusericon(imgPath.getText().toString());
        String route=gson.toJson(user);
        Log.i("info",route);
        observable = RetroFactory.getInstance().uploadMemberIcon("Kass",route,parts);
        observable.compose(composeFunction).subscribe(new BaseObserver<UserInfo>(this,pd) {

            @Override
            public void onHandleSuccess(UserInfo userInfo) {
                Toast.makeText(getApplicationContext(),"a", Toast.LENGTH_LONG).show();
            }
        });

    }
    public Uri getFileUri(Uri uri){
        if (uri.getScheme().equals("file")) {
            String path = uri.getEncodedPath();
            String TAG="info";
            Log.d(TAG, "path1 is " + path);
            if (path != null) {
                path = Uri.decode(path);
                Log.d(TAG, "path2 is " + path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(")
                        .append(MediaStore.Images.ImageColumns.DATA)
                        .append("=")
                        .append("'" + path + "'")
                        .append(")");
                Cursor cur = cr.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur
                        .moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    //do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    Log.d(TAG, "uri_temp is " + uri_temp);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }
}
