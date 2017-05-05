package phil.alan.doshwise;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class split_expenses extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_expenses);
        db = new DBHelper(this);

        // group expense totals by month
        ArrayList<String> expenseNames = new ArrayList<>();
        Cursor cursor = db.splitExpenses();
        if (cursor.moveToFirst()) {
            do {
                expenseNames.add("Person: " + cursor.getString(0) + " - Total: €" + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // add expenses to ListView
        ListView listView = (ListView) findViewById(R.id.splitList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, expenseNames);
        listView.setAdapter(adapter);

        // listeners for changing activities
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

        /*
       access database to retrieve expenses and split them
         */

        // create a list of peoples ids and use them to get name
        // create a list of expense ids and use them to get expense
        // need to display the person's name and the total of the expenses for that person
        // make it dynamic
    }
}
