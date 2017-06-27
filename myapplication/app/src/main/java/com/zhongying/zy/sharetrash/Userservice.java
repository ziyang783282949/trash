package com.zhongying.zy.sharetrash;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
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
        String path="http://115.154.116.201:8080/transfer/servlet/logInfo";
        Map<String,String> params=new HashMap<String,String>();
        params.put("name",name);
        params.put("password",pass);
        try {
            return SendGetRequest(path,params,"UTF-8",context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean SendGetRequest(String path,Map<String,String> params,String encode,Context context) throws UnsupportedEncodingException {
        StringBuilder url=new StringBuilder();
        try {
            url.append(path).append("?");
            for (Map.Entry<String,String> entry:params.entrySet()) {
                    url.append(entry.getKey()).append("=");
                    url.append(URLEncoder.encode(entry.getValue(),encode));
                    url.append("&");
                }
            url.deleteCharAt(url.length()-1);
            //Toast.makeText(context,url,Toast.LENGTH_LONG).show();
            HttpURLConnection conn= (HttpURLConnection) new URL(url.toString()).openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(conn.getResponseCode()==200){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
