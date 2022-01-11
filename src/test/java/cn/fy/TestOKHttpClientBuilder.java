package com.anfu.cloud.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;


public class TestOKHttpClientBuilder {
    public static void main(String[] args) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String url = "https://103.139.213.87:10944/api/project/sys_state";
        String json = "{'auth':'attacker@kali'}";
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(new HashMap<>()))
                .post(RequestBody.create(JSON, json))
                .build();
        OKHttpClientBuilder.buildOKHttpClient()
                .build()
                .newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("onFailure()");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        System.out.println("onResponse()");
                        System.out.println(response.body().string());
                    }
                });
    }
}