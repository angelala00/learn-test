package com.anfu.cloud.utils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginUtils {

    public static String login(String url, String userId, String platformId, String token){
        String result = "";
        String urlName = url;
        try {
            URL realUrl = new URL(urlName);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("userId", userId);
            conn.setRequestProperty("token", token);
            conn.setRequestProperty("platformId", platformId);
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "-->" + map.get(key));
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }



    public static String getUserToken(String user, String pwd){
        String sid = LoginUtils.getUserSid(user,pwd);
        String url = "http://login.prep.cloud.qihoo.net/api/open/auth/unify/login";
        String result  = LoginUtils.get(url+"?ref=http%3A%2F%2Flogin.prep.cloud.qihoo.net%2F&sid=" + sid);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getJSONObject("data").getString("token");
    }


    public static String getUserSid(String username,String pwd){
        HashMap<String, String> map = new HashMap<>();
        map.put("user",username);
        map.put("passwd",pwd);
        map.put("src","qihoo");
        map.put("ref","http://login.prep.cloud.qihoo.net?id=6");
        String result = LoginUtils.post("https://login.ops.qihoo.net:4436/sec/login",map);
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String sid = jsonObject.getString("sid");
        return sid;
    }

    public static String get(String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String resStr = null;
        try {
            Request request = new Request.Builder().get().url(url).build();
            Response response = mOkHttpClient.newCall(request).execute();
            resStr = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return resStr;
        }
        return resStr;
    }

    public static String post(String url, Map<String, String> paramsMap) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String resStr = null;
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();

            builder = builder.setType(MultipartBody.FORM);
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                builder = builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
            MultipartBody formBody = builder.build();

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),"{'auth':'attacker@kali'}");

            Request request = new Request.Builder().post(requestBody).url(url).header("accept","application/json").header("Content-Type","application/json").build();
            Response response = OkHttpUtils.buildOKHttpClient().build().newCall(request).execute();
            resStr = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return resStr;
        }
        return resStr;
    }


        public static void main(String[] args) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("auth","attacker@kali");
        String result = LoginUtils.post("https://103.139.213.87:10944/api/project/sys_state",paramMap);
        System.out.println(result);

    }

}
