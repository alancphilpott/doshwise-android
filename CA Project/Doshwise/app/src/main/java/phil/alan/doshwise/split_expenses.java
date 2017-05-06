package phil.alan.doshwise;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;

public class split_expenses extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_expenses);
        db = new DBHelper(this);
        Cursor cursor;

        // GROUP EXPENSE TOTALS BY PERSON - EXPENSE NUMBERS NEED TO BE DIVIDED BY THE NUM PEOPLE INVOLVED BEFORE BEING ADDED TO THAT PERSON TOTAL EXPENSE
        ArrayList<String> expenseNames = new ArrayList<>();
        cursor = db.splitExpenses();
        if (cursor.moveToFirst()) {
            do {
                expenseNames.add("Person: " + cursor.getString(0) + " - Total: €" + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // ADD EXPENSES TO LIST VIEW
        ListView listView = (ListView) findViewById(R.id.splitList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, expenseNames);
        listView.setAdapter(adapter);

        // LISTENERS FOR CHANGING ACTIVITIES
        Button expenses = (Button) findViewById(R.id.btn_expenses);
        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(split_expenses.this, view_expenses.class);
                startActivity(intent);
            }
        });
        Button history = (Button) findViewById(R.id.btn_history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(split_expenses.this, view_history.class);
                startActivity(intent);
            }
        });
    }

    // METHOD TO ADD OPTIONS MENU
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // METHOD FOR OPTIONS MENU ITEMS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.household: {
                Toast.makeText(split_expenses.this,"You Clicked Household",Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.about: {
                Toast.makeText(split_expenses.this,"You Clicked About",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
