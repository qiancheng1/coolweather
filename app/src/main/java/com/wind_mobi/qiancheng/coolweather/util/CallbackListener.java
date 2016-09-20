package com.wind_mobi.qiancheng.coolweather.util;

/**
 * Created by qiancheng on 2016/9/20.
 */
public interface CallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
