package com.example.ex39_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for displaying credits.
 * This activity shows the credits screen and allows the user to navigate back to the main activity.
 *
 * @author      Noa Zohar <nz2020@bs.amalnet.k12.il>
 * @version     1.0
 * @since       14/4//2025
 *
 * short description:
 *        This activity enables edge-to-edge display and shows a credits screen.
 *        It provides a simple interface with a menu that allows the user to navigate back to
 *        the main screen.
 */
public class mainCredits extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     * Enables edge-to-edge display and sets the content view to the credits screen layout.
     *
     * @param savedInstanceState a Bundle containing the activity's previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_credits);
    }

    /**
     * Creates the options menu for this activity.
     * Inflates the menu resource to display the options menu items.
     *
     * @param menu the menu in which the items should be added
     * @return true if the menu was successfully created, false otherwise
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
     * @return Return false to allow normal menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.main) {
            Intent si = new Intent(this,MainActivity.class);
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
        else if (id == R.id.delete) {
            Intent si = new Intent(this, delete_data_activity.class);
            startActivity(si);
        }

        return true;
    }
}