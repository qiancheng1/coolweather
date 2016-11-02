package com.wind_mobi.qiancheng.coolweather.util;

import org.apache.http.HttpClientConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by qiancheng on 2016/9/20.
 */
public class HttpUtil {
    URL url= null;

    public static void sendHttpRequest(final String address,final CallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(6000);
                    httpURLConnection.setReadTimeout(5000);
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader bfRead = new BufferedReader(new InputStreamReader(in));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bfRead.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(stringBuilder.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                }finally {
                    if (listener != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }
}
