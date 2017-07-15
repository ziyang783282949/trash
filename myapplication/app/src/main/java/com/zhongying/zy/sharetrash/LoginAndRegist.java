package com.zhongying.zy.sharetrash;

import android.content.Context;

import com.zhongying.zy.sharetrash.UserService.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zy on 2017/6/18.
 */

public class LoginAndRegist {
    public static String login(String name, String pass, Context context){
        String path="http://zyitem.ngrok.cc/transfer/servlet/UserLogin";
        Map<String,String> params=new HashMap<String,String>();
        params.put("username",name);
        params.put("password",pass);
        try {
            return SendGetLoinRequest(path,params,"UTF-8",context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String SendGetLoinRequest(String path,Map<String,String> params,String encode,Context context) throws UnsupportedEncodingException {
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
                user.setPassword(URLEncoder.encode(entry.getValue(),encode));
            }
        }
        try {
            js.put("username",user.getUsername());
            js.put("password",user.getPassword());
            //Log.i("info",user.getUsername());
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
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // 内容类型
            OutputStream os = conn.getOutputStream();
            final String content= String.valueOf(param);
            os.write(content.getBytes());
            os.close();

            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line="";
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            String responseCheck= URLDecoder.decode(sb.toString(),"utf-8");
            //Log.i("info",responseCheck);
            String finalContent=ParseJson(responseCheck);
            if(conn.getResponseCode()==200){
                if(finalContent.equals("success")){
                    return "success";
                }
                return "fail";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String regist(String name, String pass, Context context){
        String path="http://zyitem.ngrok.cc/transfer/servlet/UserRegist";
        Map<String,String> params=new HashMap<String,String>();
        params.put("username",name);
        params.put("password",pass);
        try {
            return SendGetRequest(path,params,"UTF-8",context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String SendGetRequest(String path,Map<String,String> params,String encode,Context context) throws UnsupportedEncodingException {
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
                user.setPassword(URLEncoder.encode(entry.getValue(),encode));
            }
        }
        try {
            js.put("username",user.getUsername());
            js.put("password",user.getPassword());
            //Log.i("info",user.getUsername());
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
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // 内容类型
            OutputStream os = conn.getOutputStream();
            final String content= String.valueOf(param);
            os.write(content.getBytes());
            //Log.i("info",content);
            os.close();

            //InputStream in=conn.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line="";
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            String responseCheck= URLDecoder.decode(sb.toString(),"utf-8");
            String finalContent=ParseJson(responseCheck);
            if(conn.getResponseCode()==200){
                //Log.i("info",finalContent);
                if(finalContent.equals("userExist")){
                    return "userExist";
                }
                return "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String ParseJson(String responseCheck) {
        try {
            JSONObject js=new JSONObject(responseCheck);
            return js.getString("check").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
