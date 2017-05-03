package phil.alan.doshwise;

import android.content.Intent;
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

        // adding listener to complete button
        Button householdToViewExpenses = (Button) findViewById(R.id.complete_button);
        householdToViewExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // assign local object variables
                EditText p_one = (EditText) findViewById(R.id.p_one);
                EditText p_two = (EditText) findViewById(R.id.p_two);
                EditText p_three = (EditText) findViewById(R.id.p_three);
                EditText house_name = (EditText) findViewById(R.id.household_name);
                String numPeople = "3";

                ArrayList<String> people = new ArrayList<String>();
                people.add(p_one.getText().toString());
                people.add(p_two.getText().toString());
                people.add(p_three.getText().toString());
                
                // validation name is not blank
                if (house_name.getText().toString().equals("")) {
                    Toast.makeText(create_household.this, "Please Enter All Fields", Toast.LENGTH_LONG).show();
                    return;
                }

                /*
                insert information into database
                - household information
                - people information one by one
                 */
                boolean houseInserted = db.insertHouseholdData(house_name.getText().toString(), numPeople);
                boolean personInserted = false;

                for (String s : people) {
                    personInserted = db.insertPeopleData(s);

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
}