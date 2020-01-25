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

public class view_history extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        db = new DBHelper(this); // OPEN DATABASE

        // GROUP EXPENSE TOTALS BY MONTH
        ArrayList<String> expenseNames = new ArrayList<>();
        Cursor cursor = db.getExpenseHistory();
        if (cursor.moveToFirst()) {
            do {
                expenseNames.add("Month: " + cursor.getString(0) + " - Total: €" + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // ADD EXPENSES TO LIST VIEW
        ListView listView = (ListView) findViewById(R.id.expensesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseNames);
        listView.setAdapter(adapter);


        // LISTENERS FOR CHANGING ACTIVITIES
        Button expenses = (Button) findViewById(R.id.btn_expenses);
        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_history.this, view_expenses.class);
                startActivity(intent);
            }
        });
        Button split = (Button) findViewById(R.id.btn_split);
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_history.this, split_expenses.class);
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

    // METHOD FOR OPTION MENU ITEMS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.household: {
                Toast.makeText(view_history.this,"You Clicked Household",Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.about: {
                Toast.makeText(view_history.this,"You Clicked About",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
