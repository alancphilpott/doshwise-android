package phil.alan.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = "Unknown";
        int phone = 0;
        String email = "Unknown";

        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("sqlite-test.db",MODE_PRIVATE,null);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Contacts");

        sqLiteDatabase.execSQL("CREATE TABLE Contacts" +
                "(name TEXT" +
                "phone INTEGER" +
                "email TEXT)");

        sqLiteDatabase.execSQL("INSERT INTO Contacts VALUES" +
                "('Tim', 65656565, 'tim@email.com')");

        sqLiteDatabase.execSQL("INSERT INTO Contacts VALUES" +
        "('Fred', 12345, 'fred@gmail.com')");

        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM Contacts",null);

        if(query.moveToFirst()) {
            name = query.getString(0);
            phone = query.getInt(1);
            email = query.getString(2);
        }
        Toast.makeText(this,"Name: " + name + " Phone No: " + phone + " Email: " + email, Toast.LENGTH_LONG).show();
        query.close();
    }
}
