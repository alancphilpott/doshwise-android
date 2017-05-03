package phil.alan.doshwise;

import android.content.Intent;
import android.database.Cursor;
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
                String monthString = getMonth(month);

                // validation everything is entered
                 if (expense_name.getText().toString().equals("") || expense_amount.getText().toString().equals("")) {
                    Toast.makeText(create_expense.this, "Please Enter All Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!ch1.isChecked() && !ch2.isChecked() && !ch3.isChecked()) {
                    Toast.makeText(create_expense.this, "Please Choose At Least One Person", Toast.LENGTH_LONG).show();
                    return;
                }

                // People Involved in the Expense
                ArrayList<Integer> peopleInvolved = new ArrayList<Integer>();
                if (ch1.isChecked())
                    peopleInvolved.add(1);
                if (ch2.isChecked())
                    peopleInvolved.add(2);
                if (ch3.isChecked())
                    peopleInvolved.add(3);

                /*
                insert information into database
                - expense information one by one for each person - below it inserts expense first, unless expense not inserting either
                 */
                boolean expenseInserted = db.insertExpensesData
                        (expense_name.getText().toString(),
                                expense_amount.getText().toString(),
                                monthString);

                boolean peInserted = false;
                Integer eid = maxExpenseID(); // meaning this should be one, thats how i know the people related to what expense num

                for (Integer i : peopleInvolved) {
                    peInserted = db.insertPEData(i, eid);

                    if (!peInserted)
                        return; // drops out here, reason? always false? both always false, dafuq
                    else
                        peInserted = true;
                }

                if (expenseInserted && peInserted)
                    Toast.makeText(create_expense.this, "Expense Created", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(create_expense.this, "Error Occurred", Toast.LENGTH_LONG).show();

                // move to next activity
                Intent intent = new Intent(create_expense.this, view_expenses.class);
                startActivity(intent);
            }
        });
    }

    // Max Expense ID
    public int maxExpenseID () {
        int maxID = 0;

        Cursor cursor = db.maxExpenseID();
        if (cursor.moveToFirst())
        {
            do
            {
                maxID = cursor.getInt(0);
            } while(cursor.moveToNext());
        }
        return maxID;
    }

    // Convert Integer To Month
    public String getMonth (int month) {
        String monthString = "";

        switch (month) {
            case 0:
                monthString = "JAN";
                break;
            case 1:
                monthString = "FEB";
                break;
            case 2:
                monthString = "MAR";
                break;
            case 3:
                monthString = "APR";
                break;
            case 4:
                monthString = "MAY";
                break;
            case 5:
                monthString = "JUN";
                break;
            case 6:
                monthString = "JUL";
                break;
            case 7:
                monthString = "AUG";
                break;
            case 8:
                monthString = "SEP";
                break;
            case 9:
                monthString = "OCT";
                break;
            case 10:
                monthString = "NOV";
                break;
            case 11:
                monthString = "DEC";
                break;
        }

        return monthString;
    }
}