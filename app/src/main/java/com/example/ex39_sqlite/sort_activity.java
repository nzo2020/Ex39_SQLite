package com.example.ex39_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class sort_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner SpiSortFilter;
    private ListView lvrecords1;
    private SQLiteDatabase db1;
    private HelperDB hlp;
    private ArrayList<String> tbl = new ArrayList<>();
    private ArrayAdapter<String> adp;
    private String[] filters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        SpiSortFilter = findViewById(R.id.SpiSortFilter);
        lvrecords1 = findViewById(R.id.lvrecords1);

        hlp = new HelperDB(this);
        db1 = hlp.getWritableDatabase();
        db1.close();

        SpiSortFilter.setOnItemSelectedListener(this);
        lvrecords1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        filters = new String[]{
                "דרך הצגת מידע",
                "להציג רק שם פרטי",
                " למיין לפי שם משפחה",
                "למיין לפי תעודת זהות "

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, filters);
        SpiSortFilter.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        tbl.clear(); // ניקוי רשימת הנתונים הקודמת
        db1 = hlp.getReadableDatabase();

        if (pos == 0) {
            return;
        }

        if (pos == 1) { // הצגת שמות פרטי
            getFirstNamesOnly();
        } else if (pos == 2) { // מיון לפי שם משפחה
            getAllEmployeesSortedByLastName();
        } else if (pos == 3) { // מיון לפי תעודת זהות
            getAllEmployeesSortedByIdNumber();
        }

        db1.close();

        // הגדרת האדפטר ל- ListView
        adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tbl);
        lvrecords1.setAdapter(adp);
    }

    // פונקציה להוציא שמות פרטי
    private void getFirstNamesOnly() {
        Cursor crsr = db1.query(Employees.TABLE_EMPLOYEES,
                new String[]{Employees.FIRST_NAME}, // only first name column
                null, null, null, null, null);

        int colFirst = crsr.getColumnIndex(Employees.FIRST_NAME);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            tbl.add(crsr.getString(colFirst)); // add only the first name to the list
            crsr.moveToNext();
        }
        crsr.close();
    }


    // פונקציה למיין לפי שם משפחה
    private void getAllEmployeesSortedByLastName() {
        Cursor crsr = db1.query(Employees.TABLE_EMPLOYEES,
                null, // all columns
                null, null, null, null,
                Employees.LAST_NAME + " ASC"); // sort by last name ascending

        int colId = crsr.getColumnIndex(Employees.CARD_ID);
        int colFirst = crsr.getColumnIndex(Employees.FIRST_NAME);
        int colLast = crsr.getColumnIndex(Employees.LAST_NAME);
        int colCompany = crsr.getColumnIndex(Employees.COMPANY);
        int colIdNumber = crsr.getColumnIndex(Employees.ID_NUMBER);
        int colPhone = crsr.getColumnIndex(Employees.PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String line = "Card ID: " + crsr.getInt(colId) +
                    ", Name: " + crsr.getString(colFirst) + " " + crsr.getString(colLast) +
                    ", Company: " + crsr.getString(colCompany) +
                    ", ID Number: " + crsr.getString(colIdNumber) +
                    ", Phone: " + crsr.getString(colPhone);
            tbl.add(line);
            crsr.moveToNext();
        }
        crsr.close();
    }



    // פונקציה למיין לפי תעודת זהות
    private void getAllEmployeesSortedByIdNumber() {
        Cursor crsr = db1.query(Employees.TABLE_EMPLOYEES,
                null, // all columns
                null, null, null, null,
                Employees.ID_NUMBER + " ASC"); // sort by ID number ascending

        int colId = crsr.getColumnIndex(Employees.CARD_ID);
        int colFirst = crsr.getColumnIndex(Employees.FIRST_NAME);
        int colLast = crsr.getColumnIndex(Employees.LAST_NAME);
        int colCompany = crsr.getColumnIndex(Employees.COMPANY);
        int colIdNumber = crsr.getColumnIndex(Employees.ID_NUMBER);
        int colPhone = crsr.getColumnIndex(Employees.PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String line = "Card ID: " + crsr.getInt(colId) +
                    ", Name: " + crsr.getString(colFirst) + " " + crsr.getString(colLast) +
                    ", Company: " + crsr.getString(colCompany) +
                    ", ID Number: " + crsr.getString(colIdNumber) +
                    ", Phone: " + crsr.getString(colPhone);
            tbl.add(line);
            crsr.moveToNext();
        }
        crsr.close();
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // לא נדרש כאן כלום
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
        else if (id == R.id.insert) {
            Intent si = new Intent(this, InsertActivity.class);
            startActivity(si);
        }
        else if (id == R.id.display) {
            Intent si = new Intent(this, DisplayActivity.class);
            startActivity(si);
        }
        else if (id == R.id.main) {
            Intent si = new Intent(this,MainActivity.class);
            startActivity(si);
        }
        else if (id == R.id.delete) {
            Intent si = new Intent(this, delete_data_activity.class);
            startActivity(si);
        }

        return true;
    }
}
