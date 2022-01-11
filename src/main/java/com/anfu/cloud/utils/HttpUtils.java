package com.anfu.cloud.utils;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;

public class HttpUtils {

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

            Request request = new Request.Builder().post(formBody).url(url).build();
            Response response = OkHttpUtils.buildOKHttpClient().build().newCall(request).execute();
            resStr = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return resStr;
        }
        return resStr;
    }


    public static void main(String[] args) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("auth", "attacker@kali");
        String result = HttpUtils.post("https://103.139.213.87:10944/api/project/sys_state", paramMap);
        System.out.println(result);

    }

}
