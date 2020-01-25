package phil.alan.doshwise;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class create_household extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_household);
        db = new DBHelper(this);

        Cursor cursor = db.getAllHouseholdData();
        if(cursor.moveToFirst()) {
            Intent intent = new Intent(create_household.this, view_expenses.class);
            startActivity(intent);
        }

        // adding listener to complete button
        Button householdToViewExpenses = (Button) findViewById(R.id.complete_button);
        householdToViewExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // assign local object variables
                EditText house_name = (EditText) findViewById(R.id.household_name);
                EditText p_one = (EditText) findViewById(R.id.p_one);
                EditText p_two = (EditText) findViewById(R.id.p_two);
                EditText p_three = (EditText) findViewById(R.id.p_three);

                // ArrayList to hold peoples names
                ArrayList<String> people = new ArrayList<String>();
                people.add(p_one.getText().toString());
                people.add(p_two.getText().toString());
                people.add(p_three.getText().toString());
                
                // validation fields are not blank
                if (house_name.getText().toString().equals("") || p_one.getText().toString().equals("") ||
                        p_two.getText().toString().equals("") || p_three.getText().toString().equals("")) {
                    Toast.makeText(create_household.this, "Please Enter All Fields", Toast.LENGTH_LONG).show();
                    return;
                }

                /*
                insert information into database
                - household information
                - people information one by one
                */

                boolean houseInserted = db.insertHouseholdData(house_name.getText().toString(), "3");

                boolean personInserted = false;
                Integer hid = maxHouseholdID(); // for assigning people to houses

                for (String s : people) {
                    personInserted = db.insertPeopleData(hid, s);

                    if (!personInserted)
                        return;
                    else
                        personInserted = true;
                }

                if (houseInserted && personInserted)
                    Toast.makeText(create_household.this, "Household Created", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(create_household.this, "Error Occurred", Toast.LENGTH_LONG).show();

                // move to next activity
                Intent intent = new Intent(create_household.this, view_expenses.class);
                startActivity(intent);
            }
        });
    }

    // Max Household ID
    public int maxHouseholdID () {
        int maxID = 0;

        Cursor cursor = db.maxHouseholdID();
        if (cursor.moveToFirst())
        {
            do
            {
                maxID = cursor.getInt(0);
            } while(cursor.moveToNext());
        }
        return maxID;
    }
}