package com.example.ex39_sqlite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.MeasureFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private HelperDB hlp;
    private AlertDialog.Builder adb;
    private LinearLayout mydialog;

    private int type;

    private EditText etCardId, etFirstName, etLastName, etCompany, etIdNumber, etPhone
            ,etMealId, etStarter, etMainCourse, etSideDish, etDessert, etDrink
            ,etProviderId, etCompanyName, etMainPhone, etSecondaryPhone
            , etOrderId, etDate, etTime, etEmployeeId;
    private String cardId, FirstName, LastName, Company, IdNumber, Phone
            ,mealId, Starter, MainCourse, SideDish, Dessert, Drink
            ,provideId,CompanyName, MainPhone, SecondaryPhone
            ,Date, Time, orderId, employeeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

    public void newEmployeeClick(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog_employee, null);
        type = 1;
        etCardId = mydialog.findViewById(R.id.etCardId);
        etFirstName = mydialog.findViewById(R.id.etFirstName);
        etLastName = mydialog.findViewById(R.id.etLastName);
        etCompany = mydialog.findViewById(R.id.etCompany);
        etIdNumber = mydialog.findViewById(R.id.etIdNumber);
        etPhone = mydialog.findViewById(R.id.etPhone);


        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Insert employee");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.setNeutralButton("Reset", myclick);

        adb.show();
    }

    public void newMealClick(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog_meal, null);
        type = 2;
        etMealId = mydialog.findViewById(R.id.etMealId);
        etStarter = mydialog.findViewById(R.id.etStarter);
        etMainCourse = mydialog.findViewById(R.id.etMainCourse);
        etSideDish = mydialog.findViewById(R.id.etSideDish);
        etDessert = mydialog.findViewById(R.id.etDessert);
        etDrink = mydialog.findViewById(R.id.etDrink);


        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Insert meal");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.setNeutralButton("Reset", myclick);

        adb.show();
    }

    public void newFoodSupplierClick(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog_meal_supplier, null);
        type = 3;
        etProviderId = mydialog.findViewById(R.id.etProviderId);
        etCompanyName = mydialog.findViewById(R.id.etCompanyName);
        etMainPhone = mydialog.findViewById(R.id.etMainPhone);
        etSecondaryPhone = mydialog.findViewById(R.id.etSecondaryPhone);

        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Insert food supplier");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.setNeutralButton("Reset", myclick);

        adb.show();
    }

    public void newOrderClick(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog_order, null);
        type = 4;
        etOrderId = mydialog.findViewById(R.id.etOrderId);
        etDate = mydialog.findViewById(R.id.etDate);
        etTime = mydialog.findViewById(R.id.etTime);
        etEmployeeId = mydialog.findViewById(R.id.etEmployeeId);
        etMealId = mydialog.findViewById(R.id.etMealId1);
        etProviderId = mydialog.findViewById(R.id.etProviderId1);

        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Insert order");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.setNeutralButton("Reset", myclick);

        adb.show();
    }

    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                if (type == 1) {
                    // new Employee
                    if (!etCardId.getText().toString().isEmpty() &&
                            !etFirstName.getText().toString().isEmpty() &&
                            !etLastName.getText().toString().isEmpty() &&
                            !etCompany.getText().toString().isEmpty() &&
                            checkId(etIdNumber.getText().toString()) &&
                            checkPhoneNumber(etPhone.getText().toString())){
                        cardId = etCardId.getText().toString();
                        FirstName = etFirstName.getText().toString();
                        LastName = etLastName.getText().toString();
                        Company = etCompany.getText().toString();
                        IdNumber = etIdNumber.getText().toString();
                        Phone = etPhone.getText().toString();

                        ContentValues cv = new ContentValues();
                        cv.put(Employees.CARD_ID, cardId);
                        cv.put(Employees.FIRST_NAME, FirstName);
                        cv.put(Employees.LAST_NAME, LastName);
                        cv.put(Employees.COMPANY, Company);
                        cv.put(Employees.ID_NUMBER, IdNumber);
                        cv.put(Employees.PHONE, Phone);
                        db = hlp.getWritableDatabase();

                        db.insert(Employees.TABLE_EMPLOYEES, null, cv);

                        db.close();

                    }
                } else if (type == 2) {
                    // new Meal
                    if (!etMealId.getText().toString().isEmpty() &&
                            !etStarter.getText().toString().isEmpty() &&
                            !etMainCourse.getText().toString().isEmpty() &&
                            !etSideDish.getText().toString().isEmpty() &&
                            !etDessert.getText().toString().isEmpty() &&
                            !etDrink.getText().toString().isEmpty()){
                        mealId = etMealId.getText().toString();
                        Starter = etStarter.getText().toString();
                        MainCourse = etMainCourse.getText().toString();
                        SideDish = etSideDish.getText().toString();
                        Dessert = etDessert.getText().toString();
                        Drink = etDrink.getText().toString();

                        ContentValues cv = new ContentValues();
                        cv.put(Meals.MEAL_ID, mealId);
                        cv.put(Meals.STARTER, Starter);
                        cv.put(Meals.MAIN_COURSE, MainCourse);
                        cv.put(Meals.SIDE_DISH, SideDish);
                        cv.put(Meals.DESSERT, Dessert);
                        cv.put(Meals.DRINK, Drink);
                        db = hlp.getWritableDatabase();

                        db.insert(Meals.TABLE_MEALS, null, cv);

                        db.close();
                    }
                } else if (type == 3) {
                    // new foodsuppler
                    if (!etProviderId.getText().toString().isEmpty() &&
                            !etCompanyName.getText().toString().isEmpty() &&
                            checkPhoneNumber(etMainPhone.getText().toString()) &&
                            checkPhoneNumber(etSecondaryPhone.getText().toString())){
                        provideId = etProviderId.getText().toString();
                        CompanyName = etCompanyName.getText().toString();
                        MainPhone = etMainPhone.getText().toString();
                        SecondaryPhone = etSecondaryPhone.getText().toString();
                        ContentValues cv = new ContentValues();
                        cv.put(mealSuppliers.PROVIDER_ID, provideId);
                        cv.put(mealSuppliers.COMPANY_NAME, CompanyName);
                        cv.put(mealSuppliers.MAIN_PHONE, MainPhone);
                        cv.put(mealSuppliers.SECONDARY_PHONE, SecondaryPhone);
                        db = hlp.getWritableDatabase();

                        db.insert(mealSuppliers.TABLE_MEAL_SUPPLIERS, null, cv);

                        db.close();
                    }

                } else if (type == 4) {
                    // new order

                    if (!etOrderId.getText().toString().isEmpty() &&
                                checkDate(etDate.getText().toString()) &&
                                checkTime(etTime.getText().toString()) &&
                                !etEmployeeId.getText().toString().isEmpty() &&
                                !etMealId.getText().toString().isEmpty() &&
                                !etProviderId.getText().toString().isEmpty()){
                        orderId = etOrderId.getText().toString();
                        Date = etDate.getText().toString();
                        Time = etTime.getText().toString();
                        employeeId = etEmployeeId.getText().toString();
                        mealId = etMealId.getText().toString();
                        provideId=etProviderId.getText().toString();
                        ContentValues cv = new ContentValues();
                        cv.put(Orders.ORDER_ID, orderId);
                        cv.put(Orders.DATE, Date);
                        cv.put(Orders.TIME, Time);
                        cv.put(Orders.EMPLOYEE_ID, employeeId);
                        cv.put(Orders.MEAL_ID, mealId);
                        cv.put(Orders.PROVIDER_ID, provideId);
                        db = hlp.getWritableDatabase();

                        db.insert(Orders.TABLE_ORDERS, null, cv);

                        db.close();
                        }
                }
            }

            if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            }

            if (which == DialogInterface.BUTTON_NEUTRAL) {
                if (type == 1) {
                    etCardId.setText("");
                    etFirstName.setText("");
                    etLastName.setText("");
                    etCompany.setText("");
                    etIdNumber.setText("");
                    etPhone.setText("");

                } else if (type == 2) {
                    etMealId.setText("");
                    etStarter.setText("");
                    etMainCourse.setText("");
                    etSideDish.setText("");
                    etDessert.setText("");
                    etDrink.setText("");

                } else if (type == 3) {
                    etProviderId.setText("");
                    etCompanyName.setText("");
                    etMainPhone.setText("");
                    etSecondaryPhone.setText("");

                } else if (type == 4) {
                    etOrderId.setText("");
                    etDate.setText("");
                    etTime.setText("");
                    etEmployeeId.setText("");
                    etMealId.setText("");
                    etProviderId.setText("");

                }
            }
        }
    };


    public boolean checkId(String id){
        return (id.length()==9 && !id.isEmpty());
    }

    public boolean checkPhoneNumber(String phone) {
        if (phone.length() != 10 || !phone.substring(0, 2).equals("05") || phone.isEmpty()) {
            return false;
        }
        return true;
    }


    public boolean checkDate(String date) {
        if (date.length() != 10 || date.charAt(2) != '/' || date.charAt(5) != '/' || date.isEmpty())
            return false;

        try {
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt(date.substring(3, 5));
            int year = Integer.parseInt(date.substring(6));

            if (month < 1 || month > 12 || day < 1)
                return false;

            int[] daysInMonth = new int[12];
            daysInMonth[0] = 31;
            daysInMonth[2] = 31;
            daysInMonth[3] = 30;
            daysInMonth[4] = 31;
            daysInMonth[5] = 30;
            daysInMonth[6] = 31;
            daysInMonth[7] = 31;
            daysInMonth[8] = 30;
            daysInMonth[9] = 31;
            daysInMonth[10] = 30;
            daysInMonth[11] = 31;

            if (isLeapYear(year)) {
                daysInMonth[1] = 29;
            } else {
                daysInMonth[1] = 28;
            }

            return day <= daysInMonth[month - 1];
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkTime(String time) {
        if (time.length() != 5 || time.charAt(2) != ':' || time.isEmpty())
            return false;

        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3));

            if (hour < 0 || hour > 23 || minute < 0 || minute > 59)
                return false;

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles item selections in the options menu.
     * Navigates to different activities based on the selected menu item.
     *
     * @param item The menu item that was selected.
     * @return Return false to allow normal menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.credits) {
            Intent si = new Intent(this,mainCredits.class);
            startActivity(si);
        }
        else if (id == R.id.main) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        else if (id == R.id.display) {
            Intent si = new Intent(this, DisplayActivity.class);
            startActivity(si);
        }
        else if (id == R.id.sort) {
            Intent si = new Intent(this,sort_activity.class);
            startActivity(si);
        }
        else if (id == R.id.delete) {
            Intent si = new Intent(this, delete_data_activity.class);
            startActivity(si);
        }

        return true;
    }
}