package phil.alan.doshwise;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class create_expense extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);
        db = new DBHelper(this);

        // adding listener to complete button
        Button householdToViewExpenses = (Button) findViewById(R.id.complete_button);
        householdToViewExpenses.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // assign local object variables
                EditText expense_name = (EditText) findViewById(R.id.expense_name);
                EditText expense_amount = (EditText) findViewById(R.id.expense_amount);
                CheckBox ch1 = (CheckBox) findViewById(R.id.ch1);
                CheckBox ch2 = (CheckBox) findViewById(R.id.ch2);
                CheckBox ch3 = (CheckBox) findViewById(R.id.ch3);

                // get current month
                Calendar c = Calendar.getInstance();
                int month = c.get(Calendar.MONTH);
                String monthString = "UN";

                if (month == 4)
                    monthString = "MAY";

                // validation everything is entered
                if (expense_name.getText().toString().equals("") || expense_amount.getText().toString().equals("")) {
                    Toast.makeText(create_expense.this, "Please Enter All Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!ch1.isChecked() && !ch2.isChecked() && !ch3.isChecked()) {
                    Toast.makeText(create_expense.this, "Please Choose At Least One Person", Toast.LENGTH_LONG).show();
                    return;
                }

                ArrayList<Integer> peopleInvolved = new ArrayList<Integer>();
                if (ch1.isChecked())
                    peopleInvolved.add(1);
                if (ch2.isChecked())
                    peopleInvolved.add(2);
                if (ch3.isChecked())
                    peopleInvolved.add(3);

                /*
                insert information into database
                - expense information one by one for each person
                 */
                for (Integer i : peopleInvolved) {
                    boolean expenseInserted = db.insertExpensesData
                            (expense_name.getText().toString(),
                            expense_amount.getText().toString(),
                            monthString);
                }

                // display confirmation toast

                // move to next activity
                Intent intent = new Intent(create_expense.this, view_expenses.class);
                startActivity(intent);
            }
        });
    }
}
