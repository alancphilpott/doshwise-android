package phil.alan.doshwise;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class view_expenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        // listeners for changing activities
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

        /* example display of list
        String[] words = {"Hello", "World"};
        ListView listView = (ListView) findViewById(R.id.expensesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        listView.setAdapter(adapter); */

        // access database for expense entries

        // add expenses to ListView
    }
}
