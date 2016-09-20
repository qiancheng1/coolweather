package com.wind_mobi.qiancheng.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qiancheng on 2016/9/17.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper{

    public static final String CREATE_PROVINCE = "create table province(" +
            "id integer primary key autoincremant," +
            "province_name text," +
            "province_code text)";

    public static final String CREATE_CITY = "create table city(" +
            "id integer primary key autoincremenr, " +
            "city_name text, " +
            "city_code text, " +
            "province_code ingeter)";

    public static final String CREATE_COUNTY= "create table county(" +
            "id integer primary key autuincrement, " +
            "county_code text, " +
            "county_name text, " +
            "city_code ingeter )";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);    //创建表
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
