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
 * DisplayActivity allows the user to select a table from the database
 * and display its contents in a ListView. It also contains a menu to navigate
 * to other activities such as inserting data or viewing credits.
 *
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/4/2025
 */
public class DisplayActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Spinner for selecting a table.
     */
    private Spinner spinTables;

    /**
     * ListView for displaying the selected table's records.
     */
    private ListView lvrecords;

    /**
     * Reference to the SQLite database.
     */
    private SQLiteDatabase db;

    /**
     * Helper class for managing the database.
     */
    private HelperDB hlp;

    /**
     * Cursor used to retrieve data from the database.
     */
    private Cursor crsr;

    /**
     * List to hold string representations of database records.
     */
    private ArrayList<String> tbl = new ArrayList<>();

    /**
     * Adapter for displaying records in the ListView.
     */
    private ArrayAdapter<String> adp;

    /**
     * Called when the activity is first created.
     * Initializes views, database, and spinner content.
     *
     * @param savedInstanceState Saved state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        spinTables = findViewById(R.id.spinTables);
        lvrecords = findViewById(R.id.lvrecords);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        spinTables.setOnItemSelectedListener(this);
        lvrecords.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        String[] tables = {"בחר טבלה להצגה", "Employees", "Meals", "FoodProviders", "Orders"};
        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tables);
        spinTables.setAdapter(adp);
    }

    /**
     * Called when a table is selected in the spinner.
     * Queries the selected table and displays its records in the ListView.
     *
     * @param adapterView The AdapterView where the selection happened
     * @param view        The view within the AdapterView that was clicked
     * @param pos         The position of the view in the adapter
     * @param id          The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        tbl.clear();
        db = hlp.getReadableDatabase();

        if (pos == 0) {
            return;
        }

        // Employees table
        if (pos == 1) {
            crsr = db.query("Employees", null, null, null, null, null, null);
            int colCardID = crsr.getColumnIndex("card_id");
            int colFirstName = crsr.getColumnIndex("first_name");
            int colLastName = crsr.getColumnIndex("last_name");
            int colCompany = crsr.getColumnIndex("company");
            int colIDNumber = crsr.getColumnIndex("id_number");
            int colPhone = crsr.getColumnIndex("phone");

            crsr.moveToFirst();
            while (!crsr.isAfterLast()) {
                int cardId = crsr.getInt(colCardID);
                String firstName = crsr.getString(colFirstName);
                String lastName = crsr.getString(colLastName);
                String company = crsr.getString(colCompany);
                String idNumber = crsr.getString(colIDNumber);
                String phone = crsr.getString(colPhone);
                String tmp = "Card ID: " + cardId + "\n" +
                        "Name: " + firstName + " " + lastName + "\n" +
                        "Company: " + company + "\n" +
                        "ID Number: " + idNumber + "\n" +
                        "Phone: " + phone;
                tbl.add(tmp);
                crsr.moveToNext();
            }
        }

        // Meals table
        else if (pos == 2) {
            crsr = db.query("Meals", null, null, null, null, null, null);
            int colMealID = crsr.getColumnIndex("meal_id");
            int colStarter = crsr.getColumnIndex("starter");
            int colMainCourse = crsr.getColumnIndex("main_course");
            int colSideDish = crsr.getColumnIndex("side_dish");
            int colDessert = crsr.getColumnIndex("dessert");
            int colDrink = crsr.getColumnIndex("drink");

            crsr.moveToFirst();
            while (!crsr.isAfterLast()) {
                int mealId = crsr.getInt(colMealID);
                String starter = crsr.getString(colStarter);
                String mainCourse = crsr.getString(colMainCourse);
                String sideDish = crsr.getString(colSideDish);
                String dessert = crsr.getString(colDessert);
                String drink = crsr.getString(colDrink);
                String tmp = "Meal ID: " + mealId + "\n" +
                        "Starter: " + starter + "\n" +
                        "Main Course: " + mainCourse + "\n" +
                        "Side Dish: " + sideDish + "\n" +
                        "Dessert: " + dessert + "\n" +
                        "Drink: " + drink;
                tbl.add(tmp);
                crsr.moveToNext();
            }
        }

        // FoodProviders table
        else if (pos == 3) {
            crsr = db.query("mealSuppliers", null, null, null, null, null, null);
            int colProviderID = crsr.getColumnIndex("provider_id");
            int colCompanyName = crsr.getColumnIndex("company_name");
            int colMainPhone = crsr.getColumnIndex("main_phone");
            int colSecondaryPhone = crsr.getColumnIndex("secondary_phone");

            crsr.moveToFirst();
            while (!crsr.isAfterLast()) {
                int providerId = crsr.getInt(colProviderID);
                String companyName = crsr.getString(colCompanyName);
                String mainPhone = crsr.getString(colMainPhone);
                String secondaryPhone = crsr.getString(colSecondaryPhone);
                String tmp = "Provider ID: " + providerId + "\n" +
                        "Company Name: " + companyName + "\n" +
                        "Main Phone: " + mainPhone + "\n" +
                        "Secondary Phone: " + secondaryPhone;
                tbl.add(tmp);
                crsr.moveToNext();
            }
        }

        // Orders table
        else if (pos == 4) {
            crsr = db.query("Orders", null, null, null, null, null, null);
            int colOrderID = crsr.getColumnIndex("order_id");
            int colDate = crsr.getColumnIndex("date");
            int colTime = crsr.getColumnIndex("time");
            int colEmployeeID = crsr.getColumnIndex("employee_id");
            int colMealID = crsr.getColumnIndex("meal_id");
            int colProviderID = crsr.getColumnIndex("provider_id");

            crsr.moveToFirst();
            while (!crsr.isAfterLast()) {
                int orderId = crsr.getInt(colOrderID);
                String date = crsr.getString(colDate);
                String time = crsr.getString(colTime);
                int employeeId = crsr.getInt(colEmployeeID);
                int mealId = crsr.getInt(colMealID);
                int providerId = crsr.getInt(colProviderID);
                String tmp = "Order ID: " + orderId + "\n" +
                        "Date: " + date + "\n" +
                        "Time: " + time + "\n" +
                        "Employee ID: " + employeeId + "\n" +
                        "Meal ID: " + mealId + "\n" +
                        "Provider ID: " + providerId;
                tbl.add(tmp);
                crsr.moveToNext();
            }
        }

        crsr.close();
        db.close();

        // Display the records in the ListView
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
        lvrecords.setAdapter(adp);
    }

    /**
     * Called when nothing is selected in the spinner.
     *
     * @param adapterView The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    /**
     * Inflates the options menu.
     *
     * @param menu The options menu in which items are placed.
     * @return true for the menu to be displayed
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
     * @return true if the event was handled, otherwise false.
     */
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.credits) {
            Intent si = new Intent(this, mainCredits.class);
            startActivity(si);
            return true;
        }
        else if (id == R.id.insert) {
            Intent si = new Intent(this, InsertActivity.class);
            startActivity(si);
            return true;
        }
        else if (id == R.id.main) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
