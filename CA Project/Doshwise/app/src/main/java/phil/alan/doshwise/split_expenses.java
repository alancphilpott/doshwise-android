package phil.alan.doshwise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class split_expenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_expenses);

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
