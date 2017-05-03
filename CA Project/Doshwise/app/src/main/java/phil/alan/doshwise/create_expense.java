package phil.alan.doshwise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class create_expense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);

        // adding listener to complete button
        Button householdToViewExpenses = (Button) findViewById(R.id.complete_button);
        householdToViewExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validation everything is entered

                /*
                insert information into database
                - expense information one by one for each person
                 */

                // display confirmation toast

                // move to next activity
                Intent intent = new Intent(create_expense.this, view_expenses.class);
                startActivity(intent);
            }
        });
    }
}
