package com.example.ex39_sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * delete_data_activity allows users to view, search, and delete employee records from the SQLite database.
 * It displays a list of employees, enables searching by input text, and provides functionality to delete selected employees
 * along with their associated orders and meals. The activity also includes a menu for navigation to other parts of the app.
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */

public class delete_data_activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * ListView to display employee records.
     */
    ListView lv;

    /**
     * EditText for entering search queries.
     */
    EditText searchInput;

    /**
     * Button to perform a search.
     */
    Button searchBtn;

    /**
     * Button to delete the selected employee.
     */
    Button deleteBtn;

    /**
     * Adapter for displaying employee data in the ListView.
     */
    ArrayAdapter<String> adp;

    /**
     * SQLiteDatabase instance for database operations.
     */
    SQLiteDatabase db;

    /**
     * HelperDB instance to manage database creation and version management.
     */
    HelperDB hlp;

    /**
     * Cursor to iterate through database query results.
     */
    Cursor crsr;

    /**
     * Index of the selected employee in the list.
     */
    int selectedIndex = -1;

    /**
     * ArrayList of employee display strings.
     */
    ArrayList<String> employeesArray, employeeIds;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState previously saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

        lv = findViewById(R.id.lv);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);

        searchInput = findViewById(R.id.searchInput);
        searchBtn = findViewById(R.id.searchBtn);
        deleteBtn = findViewById(R.id.deleteBtn);


        hlp = new HelperDB(this);
        employeesArray = new ArrayList<>();
        employeeIds = new ArrayList<>();

        getEmployees();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEmployee();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });
    }

    /**
     * Retrieves all employee records from the database and updates the ListView.
     */
    private void getEmployees() {
        db = hlp.getReadableDatabase();
        crsr = db.query(Employees.TABLE_EMPLOYEES, null, null, null, null, null, null);

        employeesArray.clear();
        employeeIds.clear();

        int colId = crsr.getColumnIndex("card_id");
        int colFirstName = crsr.getColumnIndex("first_name");
        int colLastName = crsr.getColumnIndex("last_name");
        int colCompany = crsr.getColumnIndex("company");
        int colTZ = crsr.getColumnIndex("id_number");
        int colPhone = crsr.getColumnIndex("phone");

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String record =
                    "Card ID: " + crsr.getString(colId) + "\n" +
                            "Name: " + crsr.getString(colFirstName) + " " + crsr.getString(colLastName) + "\n" +
                            "T.Z: " + crsr.getString(colTZ) + "\n" +
                            "Phone: " + crsr.getString(colPhone) + "\n" +
                            "Company: " + crsr.getString(colCompany);
            employeesArray.add(record);
            employeeIds.add(crsr.getString(colTZ));
            crsr.moveToNext();
        }
        crsr.close();
        db.close();

        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeesArray);
        lv.setAdapter(adp);
    }

    /**
     * Handles item clicks in the ListView.
     *
     * @param adapterView the AdapterView where the click happened
     * @param view the view within the AdapterView that was clicked
     * @param pos the position of the view in the adapter
     * @param rowId the row id of the item that was clicked
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long rowId) {
        selectedIndex = pos;
    }

    /**
     * Filters the employee list based on the user's search query.
     */
    private void searchEmployee() {
        String query = searchInput.getText().toString().trim().toLowerCase();
        if (query.isEmpty()) {
            getEmployees();
            return;
        }

        ArrayList<String> filtered = new ArrayList<>();
        ArrayList<String> filteredIds = new ArrayList<>();

        for (int i = 0; i < employeesArray.size(); i++) {
            String data = employeesArray.get(i).toLowerCase();
            if (data.contains(query)) {
                filtered.add(employeesArray.get(i));
                filteredIds.add(employeeIds.get(i));
            }
        }

        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filtered);
        lv.setAdapter(adp);
        employeeIds = filteredIds;
        employeesArray = filtered;
        selectedIndex = -1;
    }

    /**
     * Shows a confirmation dialog and deletes the selected employee if confirmed.
     * Also deletes the associated orders and meals from the database.
     */
    private void confirmDelete() {
        if (selectedIndex == -1 || selectedIndex >= employeeIds.size()) {
            Toast.makeText(this, "Please select an employee to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        final String employeeId = employeeIds.get(selectedIndex);
        String employeeInfo = employeesArray.get(selectedIndex);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Employee");
        builder.setMessage("Are you sure you want to delete?\n\n" + employeeInfo);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                db = hlp.getWritableDatabase();

                // delete related meals
                crsr = db.query(Orders.TABLE_ORDERS, new String[]{Orders.MEAL_ID}, Orders.EMPLOYEE_ID + "=?", new String[]{employeeId}, null, null, null);
                ArrayList<String> mealIds = new ArrayList<>();
                int mealCol = crsr.getColumnIndex(Orders.MEAL_ID);
                while (crsr.moveToNext()) {
                    mealIds.add(crsr.getString(mealCol));
                }
                crsr.close();

                for (String mealId : mealIds) {
                    db.delete(Meals.TABLE_MEALS, Meals.MEAL_ID + "=?", new String[]{mealId});
                }

                db.delete(Orders.TABLE_ORDERS, Orders.EMPLOYEE_ID + "=?", new String[]{employeeId});
                db.delete(Employees.TABLE_EMPLOYEES, Employees.ID_NUMBER + "=?", new String[]{employeeId});
                db.close();

                Toast.makeText(delete_data_activity.this, "Employee deleted", Toast.LENGTH_SHORT).show();
                getEmployees();
                selectedIndex = -1;
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    /**
     * Inflates the options menu.
     *
     * @param menu the options menu
     * @return true to display the menu
     */
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
     * @return true to consume the event here.
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
        else if (id == R.id.sort) {
            Intent si = new Intent(this,sort_activity.class);
            startActivity(si);
        }
        else if (id == R.id.main) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }

        return true;
    }
}
