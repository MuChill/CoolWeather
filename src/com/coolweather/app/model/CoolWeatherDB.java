package com.coolweather.app.model;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.db.CoolWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	//���ݿ���
	public static final String DB_NAME = "cool_weather";
	//�汾
	public static final int VERSION = 1;
	
	private static CoolWeatherDB coolWeatherDB;
	
	private SQLiteDatabase db;
    //���췽�����ɻ�
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper
				(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();//��д�����ݿ�
	}
	
	//��ȡcoolWeatherʵ������
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	//ʵ���洢�����ݿ�
	public void saveProvince(Province province){
		if(province!=null){
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
			
		}
	}
	
	//�����ݿ��ȡȫ��ʡ����Ϣ
	public List<Province> loadProvince(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query(
				"Province", null, null, null, null, null, null);
 		if(cursor.moveToFirst()){
 			do{
 				Province province = new Province();
 				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
 				province.setProvinceName(cursor.getString(cursor.getColumnIndex("provice_name")));
 				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
 			    list.add(province);
 			}while(cursor.moveToNext());
 			
 			
 		}
		return list;
		
	}
	//�洢cityʵ�������ݿ�
	public void saveCity(City city){
		if(city!=null){
			//contentvalues��������
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("provice_id", city.getProviceId());
			db.insert("city", null, values);
		}
	}
	//�����ݿ��ȡĳʡ����city��Ϣ
	public List<City> loadCities(int provinceId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query(
				"City", null,"province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);
 		if(cursor.moveToFirst()){
 			do{
 				City city= new City();
 				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
 				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
 				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
 				city.setProvinceId(provinceId);
 				list.add(city);
 			}while(cursor.moveToNext());
 		}
		return list;
		
	}

//�洢Countyʵ�������ݿ�
	public void saveCounty( County county){
		if(county!=null){
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("county", null, values);
		}
	}
	//�����ݿ��ȡĳ������county��Ϣ
	public List<County> loadCounties(int cityId){
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query(
				"County", null,"city_id=?", new String[]{String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				County county= new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("city_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("city_code")));
				county.setCityId(cityId);
				list.add(county);
			}while(cursor.moveToNext());
		}
		return list;
		
	}
}
