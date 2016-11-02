package com.wind_mobi.qiancheng.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wind_mobi.qiancheng.coolweather.model.City;
import com.wind_mobi.qiancheng.coolweather.model.County;
import com.wind_mobi.qiancheng.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

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
     *
     * @param context
     */
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper coolWeatherOpenHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = coolWeatherOpenHelper.getWritableDatabase();   //创建数据库
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
     *
     * @param province
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("province_name", province.getProvinceName());
            contentValues.put("province_code", province.getProvinceCode());
            db.insert("province", null, contentValues);
        }
    }

    /**
     * 存储省份的信息
     */
    public List<Province> loaoProvince() {
        List<Province> provinceList = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                provinceList.add(province);
            } while (cursor.moveToNext());
        }

        return provinceList;
    }

    /**
     * 将City存储到数据库中和从数据库中读取城市的信息
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", city.getId());
            contentValues.put("city_name", city.getCityName());
            contentValues.put("city_code", city.getCityCode());
            contentValues.put("province_id", city.getProvinceId());
            db.insert("city", null, contentValues);
        }
    }

    public List<City> loadCity(int id) {
        List<City> cityList = new ArrayList<>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[]{String.valueOf("provinceId")}, null, null, null);

        if (cursor.moveToFirst()) {

            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("provinceId")));
                //cityList.add(city);
                cityList.add(city);
            } while (cursor.moveToNext());
        }
        return cityList;
    }

    /**
     * 将county存储到数据库中和从数据库中读取城市的信息
     *
     * @param county
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", county.getId());
            contentValues.put("county_name", county.getCountyName());
            contentValues.put("county_code", county.getCountyCode());
            contentValues.put("city_id", county.getCityId());
            db.insert("city", null, contentValues);
        }
    }

    public List<County> loadCounty(int id) {
        List<County> countyList = new ArrayList<>(); //jiazai dao zhege biao limian
        County county = new County();
        Cursor cursor = db.query("county", null, "county_id = ?", new String[]{String.valueOf("cityId")}, null, null, null);

        while (cursor.moveToFirst()) {
            do {
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city+id")));
                countyList.add(county);
            }while (cursor.moveToNext());
        }
        return countyList;
    }
}
