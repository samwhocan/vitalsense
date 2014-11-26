package com.omniapps.vitalsense5.daos;

import android.provider.BaseColumns;

public final class VitalsenseContract {
	
	public VitalsenseContract() {
		// To prevent someone from accidentally instantiating the contract class,
		// give it an empty constructor.
	}
	
	/* Inner class that defines the table contents */
	public static abstract class ResultsEntry implements BaseColumns {
		public static final String TABLE_NAME = "results";
		public static final String COLUMN_NAME_DATETIME = "datetime";
		public static final String COLUMN_NAME_SYSTOLIC = "systolic";
		public static final String COLUMN_NAME_DIASTOLIC = "diastolic";
		public static final String COLUMN_NAME_PULSE = "pulse";
		
		public static final String COLUMN_NAME_UPDATED = _ID + " DESC";
	}
}
