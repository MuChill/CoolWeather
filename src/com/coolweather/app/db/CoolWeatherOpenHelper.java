package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	
	/**
	 * Provice�������
	 */
	public static final String CREATE_PROVICE = "creat table Provice("
			+"id integer primary key autoincrementa,"
			+"provice_name text,"
			+"provice_code text)";
	//�� city
	public static final String CREATE_CITY = "creat table city("
			+"id integer primary key autoincrementa,"
			+"city_name text,"
			+"city_code text," 
			+"provice_id integer)";
	//�� county
	public static final String CREATE_COUNTY = "creat table county("
				+"id integer primary key autoincrementa,"
				+"city_name text,"
				+"city_code text," 
				+"city_id integer)";

	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	       db.execSQL(CREATE_PROVICE);//����ʡ��
	       db.execSQL(CREATE_CITY);//������
	       db.execSQL(CREATE_COUNTY);//��
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
