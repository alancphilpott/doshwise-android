package phil.alan.doshwise;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_expenses extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);
        db = new DBHelper(this); // OPEN DATABASE

        // ACCESS DATABASE FOR EXPENSE ENTRIES
        ArrayList<String> expenseNames = new ArrayList<>();
        Cursor cursor = db.getAllExpensesData();
        if (cursor.moveToFirst()) {
            do {
                expenseNames.add(cursor.getString(0) + " : " + cursor.getString(1) + " €" + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        // ADD EXPENSES TO LIST VIEW
        final ListView listView = (ListView) findViewById(R.id.expensesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseNames);
        listView.setAdapter(adapter);

        // LISTENER FOR CLICKING ON A LIST ITEM
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listView.getItemAtPosition(position).toString();
                if (name.contains(" ")) {
                    name = name.substring(0, name.indexOf(" "));
                    deleteExpense(name);
                }
            }
        });

        // LISTENERS FOR CHANGING ACTIVITIES
        Button split = (Button) findViewById(R.id.btn_split);
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_expenses.this, split_expenses.class);
                startActivity(intent);
            }
        });
        Button history = (Button) findViewById(R.id.btn_history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_expenses.this, view_history.class);
                startActivity(intent);
            }
        });
        Button add_expense = (Button) findViewById(R.id.btn_addExpense);
        add_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_expenses.this, create_expense.class);
                startActivity(intent);
            }
        });
    }

    // METHOD TO DELETE AN EXPENSE
    public void deleteExpense(final String eid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete This Expense?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Integer deletedRowsEx = db.deleteExpenseData(eid);
                Integer deletedRowsPEx = db.deletePeopleExpenses(eid);
                if (deletedRowsEx > 0 && deletedRowsPEx > 0)
                    Toast.makeText(view_expenses.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(view_expenses.this, "Error While Deleting", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view_expenses.this, view_expenses.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(view_expenses.this,"Expense Kept",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // METHOD TO ADD OPTIONS MENU
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // METHOD FOR OPTION MENU ITEMS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.household: {
                Toast.makeText(view_expenses.this,"You Clicked Household",Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.about: {
                Toast.makeText(view_expenses.this,"You Clicked About",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
