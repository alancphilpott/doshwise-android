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

public class view_history extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        db = new DBHelper(this);

        // group expense totals by month
        ArrayList<String> expenseNames = new ArrayList<>();
        Cursor cursor = db.getExpenseHistory();
        if (cursor.moveToFirst()) {
            do {
                expenseNames.add("Month: " + cursor.getString(0) + " - Total: €" + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // add expenses to ListView
        ListView listView = (ListView) findViewById(R.id.expensesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, expenseNames);
        listView.setAdapter(adapter);


        // listeners for changing activities
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
}
