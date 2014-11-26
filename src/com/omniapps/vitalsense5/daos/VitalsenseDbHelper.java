package com.omniapps.vitalsense5.daos;

import com.omniapps.vitalsense5.daos.VitalsenseContract.ResultsEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VitalsenseDbHelper extends SQLiteOpenHelper {
	// If you change the database schema, you must increment the database version.
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Vitalsense.db";
	
	private static final String EMPTY_STRING = "";
	private static final String LINE_BREAK = "\n";
	
	private static final String TEXT_TYPE = " TEXT NOT NULL";
	private static final String COMMA = ", ";
	
	private static final String SQL_CREATE_ENTRIES = 
			"CREATE TABLE IF NOT EXISTS " + ResultsEntry.TABLE_NAME + " ( " +
				ResultsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				ResultsEntry.COLUMN_NAME_DATETIME + TEXT_TYPE + COMMA +
				ResultsEntry.COLUMN_NAME_SYSTOLIC + TEXT_TYPE + COMMA +
				ResultsEntry.COLUMN_NAME_DIASTOLIC + TEXT_TYPE + COMMA +
				ResultsEntry.COLUMN_NAME_PULSE + TEXT_TYPE + " );"; //+
			//"ORDER BY " + ResultsEntry._ID + " DESC";
	
	private static final String SQL_DELETE_ENTRIES = 
			"DROP TABLE IF EXISTS " + ResultsEntry.TABLE_NAME;

	public VitalsenseDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	public long createEntry(String time, String systolic, String diastolic, String pulse) {
		SQLiteDatabase db = getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(ResultsEntry.COLUMN_NAME_DATETIME, time);
		contentValues.put(ResultsEntry.COLUMN_NAME_SYSTOLIC, systolic);
		contentValues.put(ResultsEntry.COLUMN_NAME_DIASTOLIC, diastolic);
		contentValues.put(ResultsEntry.COLUMN_NAME_PULSE, pulse);
		
		long newRowId = db.insert(ResultsEntry.TABLE_NAME, null, contentValues);
		
		return newRowId;
	}
	
	public String getTime(SQLiteDatabase db) {
		
		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = new String[] { 
				ResultsEntry._ID, 
				ResultsEntry.COLUMN_NAME_DATETIME, 
				ResultsEntry.COLUMN_NAME_SYSTOLIC, 
				ResultsEntry.COLUMN_NAME_DIASTOLIC, 
				ResultsEntry.COLUMN_NAME_PULSE 
		};
		
		String sortOrder = ResultsEntry.COLUMN_NAME_UPDATED;
		
		Cursor cursor = db.query(ResultsEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);
		
		/*cursor.moveToFirst();
		String dateTime = cursor.getString(
				cursor.getColumnIndexOrThrow(ResultsEntry.COLUMN_NAME_DATETIME)
		);*/
		
		String result = EMPTY_STRING;
		int iTime = cursor.getColumnIndex(ResultsEntry.COLUMN_NAME_DATETIME);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			result = result + cursor.getString(iTime) + LINE_BREAK;
		}

		return result;
	}
	
	public String getSystolic(SQLiteDatabase db) {
		
		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = new String[] { 
				ResultsEntry._ID, 
				ResultsEntry.COLUMN_NAME_DATETIME, 
				ResultsEntry.COLUMN_NAME_SYSTOLIC, 
				ResultsEntry.COLUMN_NAME_DIASTOLIC, 
				ResultsEntry.COLUMN_NAME_PULSE 
		};
		
		String sortOrder = ResultsEntry.COLUMN_NAME_UPDATED;
		
		Cursor cursor = db.query(ResultsEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);
		
		/*cursor.moveToFirst();
		String systolic = cursor.getString(
				cursor.getColumnIndexOrThrow(ResultsEntry.COLUMN_NAME_SYSTOLIC)
		);*/
		
		String result = EMPTY_STRING;
		int iSystolic = cursor.getColumnIndex(ResultsEntry.COLUMN_NAME_SYSTOLIC);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			result = result + cursor.getString(iSystolic) + LINE_BREAK;
		}

		return result;
	}
	
	public String getDiastolic(SQLiteDatabase db) {
		
		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = new String[] { 
				ResultsEntry._ID, 
				ResultsEntry.COLUMN_NAME_DATETIME, 
				ResultsEntry.COLUMN_NAME_SYSTOLIC, 
				ResultsEntry.COLUMN_NAME_DIASTOLIC, 
				ResultsEntry.COLUMN_NAME_PULSE 
		};
		
		String sortOrder = ResultsEntry.COLUMN_NAME_UPDATED;
		
		Cursor cursor = db.query(ResultsEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);
		
		/*cursor.moveToFirst();
		String diastolic = cursor.getString(
				cursor.getColumnIndexOrThrow(ResultsEntry.COLUMN_NAME_DIASTOLIC)
		);*/

		String result = EMPTY_STRING;
		int iDiastolic = cursor.getColumnIndex(ResultsEntry.COLUMN_NAME_DIASTOLIC);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			result = result + cursor.getString(iDiastolic) + LINE_BREAK;
		}

		return result;
	}
	
	public String getPulse(SQLiteDatabase db) {
		
		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = new String[] { 
				ResultsEntry._ID, 
				ResultsEntry.COLUMN_NAME_DATETIME, 
				ResultsEntry.COLUMN_NAME_SYSTOLIC, 
				ResultsEntry.COLUMN_NAME_DIASTOLIC, 
				ResultsEntry.COLUMN_NAME_PULSE 
		};
		
		String sortOrder = ResultsEntry.COLUMN_NAME_UPDATED;
		
		Cursor cursor = db.query(ResultsEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);
		
		/*cursor.moveToFirst();
		String pulse = cursor.getString(
				cursor.getColumnIndexOrThrow(ResultsEntry.COLUMN_NAME_PULSE)
		);*/

		String result = EMPTY_STRING;
		int iPulse = cursor.getColumnIndex(ResultsEntry.COLUMN_NAME_DIASTOLIC);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			result = result + cursor.getString(iPulse) + LINE_BREAK;
		}

		return result;
	}
}
