package com.anfu.cloud.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anfu.common.DataProvider;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;



public class HttpRequest {


    public static String sendGet(String url, String param, String platformId,String token) {
        String result = "";
        String urlName = url;
        try {
            URL realUrl = new URL(urlName);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("userId", param);
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

    public static int getResponseCode(String url, String param, String platformId) {
        int statusCode = -1;
        String urlName = url;
        try {
            URL realUrl = new URL(urlName);
            URLConnection conn = realUrl.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("userId", param);
            conn.setRequestProperty("token", "anfu_test");
            conn.setRequestProperty("platformId", platformId);
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            statusCode = ((HttpURLConnection) conn).getResponseCode();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }

    public static JSONObject doPost(String url, String userId, JSONArray jsonObject, String platformId) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;

        //添加header
        post.addHeader("userId", userId);
        post.addHeader("token", "anfu_test");
        post.addHeader("platformId", platformId);

        try {
            StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(entity);
                response = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    public static JSONObject doPostToken(String url, String userId, String token, JSONObject jsonObject, String platformId) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;

        //添加header
        post.addHeader("userId", userId);
        post.addHeader("token", token);
        post.addHeader("platformId", platformId);

        try {
            StringEntity s = new StringEntity(jsonObject.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(entity);
                response = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
