package com.wind_mobi.qiancheng.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wind_mobi.qiancheng.coolweather.model.Province;

/**
 * Created by qiancheng on 2016/9/17.
 */
public class CoolWeatherDB {
    private static final int VERSION = 1;
    private static String DB_NAME = "cool_weather";
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    /**
     * 构造方法私有化
     * @param context
     */
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper coolWeatherOpenHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = coolWeatherOpenHelper.getWritableDatabase();
    }

    /**
     * 获取coolweather实例
     */
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (null == coolWeatherDB) {
            CoolWeatherDB coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    /**
     * 存储省份信息.
     * @param province
     */
    public void saveProvince(Province province) {
        if (province != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("province_name", province.getProvinceName());
            contentValues.put("province_code", province.getProvinceCode());
            db.insert("province", null, contentValues);
        }
    }

    /**
     * 一下的方法分别存储市信息和乡镇信息.
     */
    public


}
