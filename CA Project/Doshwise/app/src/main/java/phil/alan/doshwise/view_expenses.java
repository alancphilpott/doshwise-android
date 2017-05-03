package phil.alan.doshwise;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class view_expenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        // listeners for changing activities

        // listener for adding expense

        // example display of list
        String[] words = {"Hello", "World"};
        ListView listView = (ListView) findViewById(R.id.expensesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        listView.setAdapter(adapter);

        // access database for expense entries

        // add expenses to ListView
    }
}
