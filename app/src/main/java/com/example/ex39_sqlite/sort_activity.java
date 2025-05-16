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

/**
 * Activity that displays employee records from the database
 * with options to sort or filter the displayed data using a Spinner.
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */
public class sort_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    /**
     * Spinner widget to select sorting/filtering options.
     */
    private Spinner SpiSortFilter;

    /**
     * ListView to display employee records.
     */
    private ListView lvrecords1;

    /**
     * SQLiteDatabase instance for database operations.
     */
    private SQLiteDatabase db1;

    /**
     * Database helper instance to manage database creation and version management.
     */
    private HelperDB hlp;

    /**
     * ArrayList holding the data strings to be displayed in the ListView.
     */
    private ArrayList<String> tbl = new ArrayList<>();

    /**
     * ArrayAdapter to bind the ArrayList data to the ListView.
     */
    private ArrayAdapter<String> adp;

    /**
     * Array of sorting/filtering options shown in the Spinner.
     */
    private String[] filters;

    /**
     * Called when the activity is starting.
     * Initializes UI components, database helper, and sets up the Spinner adapter and listener.
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state.
     */
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
                "דרך הצגת מידע",           // Display method
                "להציג רק שם פרטי",       // Show first name only
                " למיין לפי שם משפחה",     // Sort by last name
                "למיין לפי תעודת זהות "    // Sort by ID number
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, filters);
        SpiSortFilter.setAdapter(adapter);
    }

    /**
     * Called when an item in the Spinner is selected.
     * Performs different database queries and updates the ListView based on the selected filter.
     *
     * @param parent The AdapterView where the selection happened.
     * @param view The view within the AdapterView that was clicked.
     * @param pos The position of the selected item.
     * @param id The row id of the selected item.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        tbl.clear(); // Clear previous data

        db1 = hlp.getReadableDatabase();

        if (pos == 0) {
            return;
        }

        if (pos == 1) {
            getFirstNamesOnly();
        } else if (pos == 2) {
            getAllEmployeesSortedByLastName();
        } else if (pos == 3) {
            getAllEmployeesSortedByIdNumber();
        }

        db1.close();

        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
        lvrecords1.setAdapter(adp);
    }

    /**
     * Retrieves and adds only first names of employees from the database to the list.
     */
    private void getFirstNamesOnly() {
        Cursor crsr = db1.query(Employees.TABLE_EMPLOYEES,
                new String[]{Employees.FIRST_NAME}, // Select only FIRST_NAME column
                null, null, null, null, null);

        int colFirst = crsr.getColumnIndex(Employees.FIRST_NAME);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            tbl.add(crsr.getString(colFirst)); // Add first names to list
            crsr.moveToNext();
        }
        crsr.close();
    }

    /**
     * Retrieves and adds all employee records sorted by last name in ascending order.
     * The data includes card ID, full name, company, ID number, and phone.
     */
    private void getAllEmployeesSortedByLastName() {
        Cursor crsr = db1.query(Employees.TABLE_EMPLOYEES,
                null, // Select all columns
                null, null, null, null,
                Employees.LAST_NAME + " ASC"); // Sort ascending by last name

        int colId = crsr.getColumnIndex(Employees.CARD_ID);
        int colFirst = crsr.getColumnIndex(Employees.FIRST_NAME);
        int colLast = crsr.getColumnIndex(Employees.LAST_NAME);
        int colCompany = crsr.getColumnIndex(Employees.COMPANY);
        int colIdNumber = crsr.getColumnIndex(Employees.ID_NUMBER);
        int colPhone = crsr.getColumnIndex(Employees.PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String line = "Card ID: " + crsr.getInt(colId) + "\n" +
                    "Name: " + crsr.getString(colFirst) + " " + crsr.getString(colLast) + "\n" +
                    "Company: " + crsr.getString(colCompany) + "\n" +
                    "T.Z: " + crsr.getString(colIdNumber) + "\n" +
                    "Phone: " + crsr.getString(colPhone);
            tbl.add(line);
            crsr.moveToNext();
        }
        crsr.close();
    }

    /**
     * Retrieves and adds all employee records sorted by ID number in ascending order.
     * The data includes card ID, full name, ID number, phone, and company.
     */
    private void getAllEmployeesSortedByIdNumber() {
        Cursor crsr = db1.query(Employees.TABLE_EMPLOYEES,
                null, // Select all columns
                null, null, null, null,
                Employees.ID_NUMBER + " ASC"); // Sort ascending by ID number

        int colId = crsr.getColumnIndex(Employees.CARD_ID);
        int colFirst = crsr.getColumnIndex(Employees.FIRST_NAME);
        int colLast = crsr.getColumnIndex(Employees.LAST_NAME);
        int colCompany = crsr.getColumnIndex(Employees.COMPANY);
        int colIdNumber = crsr.getColumnIndex(Employees.ID_NUMBER);
        int colPhone = crsr.getColumnIndex(Employees.PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String line = "Card ID: " + crsr.getInt(colId) + "\n" +
                    "Name: " + crsr.getString(colFirst) + " " + crsr.getString(colLast) + "\n" +
                    "T.Z: " + crsr.getString(colIdNumber) + "\n" +
                    "Phone: " + crsr.getString(colPhone) + "\n" +
                    "Company: " + crsr.getString(colCompany);
            tbl.add(line);
            crsr.moveToNext();
        }
        crsr.close();
    }

    /**
     * Called when no item is selected in the Spinner.
     * Currently does nothing.
     *
     * @param parent The AdapterView where the selection disappeared from.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No action needed
    }

    /**
     * Inflates the options menu when the activity starts.
     *
     * @param menu The options menu in which items are placed.
     * @return true to display the menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles clicks on the options menu items.
     * Starts different activities depending on the menu selection.
     *
     * @param item The selected menu item.
     * @return true to indicate the event was handled.
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
