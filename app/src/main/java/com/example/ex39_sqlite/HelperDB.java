package com.example.ex39_sqlite;

import static com.example.ex39_sqlite.Employees.TABLE_EMPLOYEES;
import static com.example.ex39_sqlite.Meals.TABLE_MEALS;
import static com.example.ex39_sqlite.Orders.TABLE_ORDERS;
import static com.example.ex39_sqlite.mealSuppliers.TABLE_MEAL_SUPPLIERS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * HelperDB class helps manage the SQLite database.
 * It is responsible for creating tables and upgrading the database schema when needed.
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */
public class HelperDB extends SQLiteOpenHelper {

    /** Database name */
    private static final String DATABASE_NAME = "dbexam.db";

    /** Database version */
    private static final int DATABASE_VERSION = 2;

    /** Helper strings to hold SQL statements */
    private String strCreate, strDelete;

    /**
     * Constructor for HelperDB.
     *
     * @param context The context of the application, used to access app resources and database
     */
    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * Responsible for creating Employees, Meals, mealSuppliers, and Orders tables.
     *
     * @param db The SQLite database instance where SQL commands are executed
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate = "CREATE TABLE " + Employees.TABLE_EMPLOYEES;
        strCreate += " (" + Employees.CARD_ID + " TEXT,";
        strCreate += " " + Employees.FIRST_NAME + " TEXT,";
        strCreate += " " + Employees.LAST_NAME + " TEXT,";
        strCreate += " " + Employees.COMPANY + " TEXT,";
        strCreate += " " + Employees.ID_NUMBER + " TEXT,";
        strCreate += " " + Employees.PHONE + " TEXT";
        strCreate += ");";
        db.execSQL(strCreate);

        strCreate = "CREATE TABLE " + Meals.TABLE_MEALS;
        strCreate += " (" + Meals.MEAL_ID + " TEXT,";
        strCreate += " " + Meals.STARTER + " TEXT,";
        strCreate += " " + Meals.MAIN_COURSE + " TEXT,";
        strCreate += " " + Meals.SIDE_DISH + " TEXT,";
        strCreate += " " + Meals.DESSERT + " TEXT,";
        strCreate += " " + Meals.DRINK + " TEXT";
        strCreate += ");";
        db.execSQL(strCreate);

        strCreate = "CREATE TABLE " + mealSuppliers.TABLE_MEAL_SUPPLIERS;
        strCreate += " (" + mealSuppliers.PROVIDER_ID + " TEXT,";
        strCreate += " " + mealSuppliers.COMPANY_NAME + " TEXT,";
        strCreate += " " + mealSuppliers.MAIN_PHONE + " TEXT,";
        strCreate += " " + mealSuppliers.SECONDARY_PHONE + " TEXT";
        strCreate += ");";
        db.execSQL(strCreate);

        strCreate = "CREATE TABLE " + Orders.TABLE_ORDERS;
        strCreate += " (" + Orders.ORDER_ID + " TEXT,";
        strCreate += " " + Orders.DATE + " TEXT,";
        strCreate += " " + Orders.TIME + " TEXT,";
        strCreate += " " + Orders.EMPLOYEE_ID + " TEXT,";
        strCreate += " " + Orders.MEAL_ID + " TEXT,";
        strCreate += " " + Orders.PROVIDER_ID + " TEXT";
        strCreate += ");";
        db.execSQL(strCreate);
    }

    /**
     * Called when the database needs to be upgraded.
     * This implementation drops all existing tables and recreates them.
     *
     * @param db     The SQLite database instance
     * @param oldVer The old database version number
     * @param newVer The new database version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        strDelete = "DROP TABLE IF EXISTS " + TABLE_EMPLOYEES;
        db.execSQL(strDelete);

        strDelete = "DROP TABLE IF EXISTS " + TABLE_MEALS;
        db.execSQL(strDelete);

        strDelete = "DROP TABLE IF EXISTS " + TABLE_MEAL_SUPPLIERS;
        db.execSQL(strDelete);

        strDelete = "DROP TABLE IF EXISTS " + TABLE_ORDERS;
        db.execSQL(strDelete);

        onCreate(db);
    }
}
