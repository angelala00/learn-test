package com.anfu.cloud.utils;

import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {
    public static OkHttpClient.Builder buildOKHttpClient() {
        try {
            TrustManager[] trustAllCerts = buildTrustManagers();
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            return builder;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
            return new OkHttpClient.Builder();
        }
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }

    private static String  get(String url,String platformId,String userId,String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        request.headers().newBuilder().add("platformId",platformId);
        request.headers().newBuilder().add("userId",userId);
        request.headers().newBuilder().add("token",token);
        Response response = client.newCall(request).execute();
        String string =response.body().string();
        return string;
    }


    public static void main(String[] args) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String url = "https://103.139.213.87:10944/api/project/sys_state";
        String json = "{'auth':'attacker@kali'}";
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(new HashMap<>()))
                .post(RequestBody.create(JSON, json))
                .build();

       // OKHttpClientBuilder.buildOKHttpClient()
         //       .build()
               new OkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("onFailure()");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        System.out.println("onResponse()");
                    }
                });
    }
}

