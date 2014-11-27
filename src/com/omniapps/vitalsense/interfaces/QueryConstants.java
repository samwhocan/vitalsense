package com.omniapps.vitalsense.interfaces;

public interface QueryConstants {	
	/*public static final String CREATE_TABLE = 
			"CREATE TABLE ";
	
	public static final String CREATE_TABLE_IF_NOT_EXISTS = 
			"CREATE TABLE IF NOT EXISTS ";
	
	public static final String OPENING_PAREN = 
			" ( ";
	
	public static final String INTEGER_PRIMARY_KEY_AUTOINCREMENT = 
			" INTEGER PRIMARY KEY AUTOINCREMENT, ";
	
	public static final String TEXT_NOT_NULL = 
			" TEXT NOT NULL, ";
	
	public static final String TEXT_NOT_NULL_PLUS_CLOSING_PAREN_AND_ORDER_BY = 
			" TEXT NOT NULL ORDER BY ";
	
	public static final String DESC = 
			" DESC)";*/
	
	public static final String DROP_TABLE_IF_EXISTS = 
			"DROP TABLE IF EXISTS ";
	
	public static final String CREATE_TABLE_TEST = 
			"CREATE TABLE IF NOT EXISTS DATABASE_TABLE ( " +
					"ROWID INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"DATETIME TEXT NOT NULL, " +
					"SYSTOLIC TEXT NOT NULL, " +	
					"DIASTOLIC TEXT NOT NULL, " +
					"PULSE TEXT NOT NULL ) " +
			"ORDER BY DATETIME DESC;";
}