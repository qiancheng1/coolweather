package com.wind_mobi.qiancheng.coolweather.util;

import android.text.TextUtils;

import com.wind_mobi.qiancheng.coolweather.db.CoolWeatherDB;
import com.wind_mobi.qiancheng.coolweather.model.City;
import com.wind_mobi.qiancheng.coolweather.model.County;
import com.wind_mobi.qiancheng.coolweather.model.Province;

/**
 * Created by qiancheng on 2016/9/20.
 */
public class Utility {

    /**
     * 解析和处理服务器返回的省级的数据
     */
    public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvince = response.split(",");
            if (allProvince != null && allProvince.length > 0) {
                for (String p : allProvince) {
                    String[] array = response.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCityResponse(CoolWeatherDB coolWeatherDB, String response,int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCity = response.split(",");
            if (allCity != null && allCity.length > 0) {
                for (String c : allCity) {
                    City city = new City();
                    String[] array = response.split("\\|");
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCountry(CoolWeatherDB coolWeatherDB, String response, int cityId) {
        if (TextUtils.isEmpty(response)) {
            String[] allCounty = response.split(",");
            if (allCounty != null && allCounty.length > 0) {
                String[] array = response.split("\\|");
                for (String co : allCounty) {
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}