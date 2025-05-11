package com.example.ex39_sqlite;

import static com.example.ex39_sqlite.Employees.TABLE_EMPLOYEES;
import static com.example.ex39_sqlite.Meals.TABLE_MEALS;
import static com.example.ex39_sqlite.Orders.TABLE_ORDERS;
import static com.example.ex39_sqlite.mealSuppliers.TABLE_MEAL_SUPPLIERS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * The type HelperDB
 */
public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    private String strCreate, strDelete;

    /**
     * Instantiates a new HelperDB
     *
     * @param context the context
     */
    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate
     * <p>
     * This method create the tables in database
     * @param db the SQLite database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_EMPLOYEES;
        strCreate+=" ("+Employees.CARD_ID+"TEXT";
        strCreate+=" "+Employees.FIRST_NAME+" TEXT,";
        strCreate+=" "+Employees.LAST_NAME+" TEXT,";
        strCreate+=" "+Employees.COMPANY+" TEXT";
        strCreate+=" "+Employees.ID_NUMBER+" TEXT";
        strCreate+=" "+Employees.PHONE+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_MEALS;
        strCreate+=" ("+Meals.MEAL_ID+"TEXT";
        strCreate+=" ("+Meals.STARTER+"TEXT";
        strCreate+=" ("+Meals.MAIN_COURSE+"TEXT";
        strCreate+=" ("+Meals.SIDE_DISH+"TEXT";
        strCreate+=" ("+Meals.DESSERT+"TEXT";
        strCreate+=" ("+Meals.DRINK+"TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_MEAL_SUPPLIERS;
        strCreate+=" ("+mealSuppliers.PROVIDER_ID+"TEXT";
        strCreate+=" ("+mealSuppliers.COMPANY_NAME+"TEXT";
        strCreate+=" ("+mealSuppliers.MAIN_PHONE+"TEXT";
        strCreate+=" ("+mealSuppliers.SECONDARY_PHONE+"TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_ORDERS;
        strCreate+=" ("+Orders.ORDER_ID+"TEXT";
        strCreate+=" ("+Orders.DATE+"TEXT";
        strCreate+=" ("+Orders.TIME+"TEXT";
        strCreate+=" ("+Orders.EMPLOYEE_ID+"TEXT";
        strCreate+=" ("+Orders.MEAL_ID+"TEXT";
        strCreate+=" ("+Orders.PROVIDER_ID+"TEXT";
        strCreate+=");";
        db.execSQL(strCreate);


    }

    /**
     * onUpgrade
     * <p>
     * This method upgrade the database
     * @param db the SQLite database
     * @param oldVer the old version code
     * @param newVer the new version code
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        strDelete="DROP TABLE IF EXISTS "+TABLE_EMPLOYEES;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_MEALS;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_MEAL_SUPPLIERS;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_ORDERS;
        db.execSQL(strDelete);
        onCreate(db);
    }
}