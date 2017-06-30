package com.zhongying.zy.sharetrash;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.zhongying.zy.sharetrash.model.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zy on 2017/6/18.
 */

public class Userservice {
    public static boolean check(String name, String pass, Context context){
        String path="http://zyitem.ngrok.cc/transfer/servlet/logInfo";
        Map<String,String> params=new HashMap<String,String>();
        params.put("username",name);
        params.put("password",pass);
        try {
            return SendGetRequest(path,params,"UTF-8",context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean SendGetRequest(String path,Map<String,String> params,String encode,Context context) throws UnsupportedEncodingException {
        JSONObject js=new JSONObject();
        JSONObject param=new JSONObject();
        UserInfo user=new UserInfo();
        for (Map.Entry<String,String> entry:params.entrySet()) {
            //user=new UserInfo();
            String name="username";
            String password="password";
            if(name.equals(entry.getKey())){
                user.setUsername(URLEncoder.encode(entry.getValue(),encode));

            }
            if(password.equals(entry.getKey())){
                user.setUserpass(URLEncoder.encode(entry.getValue(),encode));
            }
        }
        try {

            js.put("username",user.getUsername());
            js.put("password",user.getUserpass());
            param.put("userInfo",js);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringBuilder url=new StringBuilder();
        try {
            url.append(path);
            HttpURLConnection conn= (HttpURLConnection) new URL(url.toString()).openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // 内容类型
            OutputStream os = conn.getOutputStream();
            final String content=String.valueOf(param);
            os.write(content.getBytes());
            os.close();
            if(conn.getResponseCode()==200){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
